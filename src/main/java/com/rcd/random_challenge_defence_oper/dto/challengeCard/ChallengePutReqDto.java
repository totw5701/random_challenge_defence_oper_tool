package com.rcd.random_challenge_defence_oper.dto.challengeCard;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChallengePutReqDto {

    private Long id;
    private String title;
    private String description;
    private String finalGoal;
    private Integer difficulty;
    private Integer assignScore;
    private List<String> challengeSubGoals;
    private Long challengeCardCategoryId;
    private Long image;
    private Integer experience;
}
