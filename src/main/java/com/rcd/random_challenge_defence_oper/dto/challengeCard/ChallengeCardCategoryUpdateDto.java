package com.rcd.random_challenge_defence_oper.dto.challengeCard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChallengeCardCategoryUpdateDto {

    private Long id;
    private String title;
    private String description;

}
