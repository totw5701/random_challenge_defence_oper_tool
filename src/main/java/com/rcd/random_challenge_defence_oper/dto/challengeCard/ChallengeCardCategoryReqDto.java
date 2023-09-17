package com.rcd.random_challenge_defence_oper.dto.challengeCard;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class ChallengeCardCategoryReqDto {

    private String title;
    private String description;
}
