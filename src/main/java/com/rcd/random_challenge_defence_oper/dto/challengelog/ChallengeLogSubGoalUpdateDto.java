package com.rcd.random_challenge_defence_oper.dto.challengelog;

import com.rcd.random_challenge_defence_oper.domain.challengecardsubgoal.ChallengeLogSubGoalStatus;
import lombok.Data;

@Data
public class ChallengeLogSubGoalUpdateDto {

    private Long id;
    private ChallengeLogSubGoalStatus status;
}
