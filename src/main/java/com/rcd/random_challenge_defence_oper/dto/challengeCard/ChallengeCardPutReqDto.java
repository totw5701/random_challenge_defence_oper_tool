package com.rcd.random_challenge_defence_oper.dto.challengeCard;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChallengeCardPutReqDto {

    private Long id;
    private String title;
    private String description;
    private String finalGoal;
    private Integer difficulty;
    private Integer assignScore;
    private List<String> challengeSubGoals;
    private Long challengeCardCategoryId;
    private Integer experience;
    private List<Long> memberPersonalityList;
}
