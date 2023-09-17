package com.rcd.random_challenge_defence_oper.dto.challengeCard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeSubGoalDetailDto {

    private Long id;
    private String subGoal;
}
