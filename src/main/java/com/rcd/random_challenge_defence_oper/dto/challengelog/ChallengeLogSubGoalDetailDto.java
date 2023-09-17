package com.rcd.random_challenge_defence_oper.dto.challengelog;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChallengeLogSubGoalDetailDto {

    private Long id;
    private String subGoal;
    private String status;
}
