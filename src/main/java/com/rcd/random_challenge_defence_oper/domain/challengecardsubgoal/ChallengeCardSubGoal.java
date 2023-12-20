package com.rcd.random_challenge_defence_oper.domain.challengecardsubgoal;

import com.rcd.random_challenge_defence_oper.domain.challengecard.ChallengeCard;
import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeSubGoalDetailDto;
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
public class ChallengeCardSubGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "challenge_card_id")
    private ChallengeCard challengeCard;

    private String subGoal;

    public ChallengeSubGoalDetailDto toDto() {
        return ChallengeSubGoalDetailDto.builder()
                .id(this.id)
                .subGoal(this.subGoal)
                .build();
    }

}
