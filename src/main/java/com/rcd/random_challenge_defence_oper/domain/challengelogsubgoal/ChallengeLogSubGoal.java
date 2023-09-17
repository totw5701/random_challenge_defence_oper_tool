package com.rcd.random_challenge_defence_oper.domain.challengelogsubgoal;

import com.rcd.random_challenge_defence_oper.domain.challengecardsubgoal.ChallengeCardSubGoal;
import com.rcd.random_challenge_defence_oper.domain.challengecardsubgoal.ChallengeLogSubGoalStatus;
import com.rcd.random_challenge_defence_oper.domain.challengelog.ChallengeLog;
import com.rcd.random_challenge_defence_oper.dto.challengelog.ChallengeLogSubGoalDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChallengeLogSubGoal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChallengeCardSubGoal challengeCardSubGoal;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChallengeLog challengeLog;

    @Enumerated(EnumType.STRING)
    private ChallengeLogSubGoalStatus challengeLogSubGoalStatus;


    public ChallengeLogSubGoalDetailDto toDetail() {
        return ChallengeLogSubGoalDetailDto.builder()
                .id(id)
                .subGoal(challengeCardSubGoal.getSubGoal())
                .status(challengeLogSubGoalStatus.getCode())
                .build();
    }

    public void statusChange(ChallengeLogSubGoalStatus status) {
        this.challengeLogSubGoalStatus = status;
    }
}
