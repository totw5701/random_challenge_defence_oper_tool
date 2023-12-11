package com.rcd.random_challenge_defence_oper.dto.challengeCard;

import com.rcd.random_challenge_defence_oper.dto.challengeCategory.ChallengeCardCategoryDetailDto;
import com.rcd.random_challenge_defence_oper.dto.file.FileDetailDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChallengeDetailDto {

    private Long id;
    private String title;
    private String description;
    private String finalGoal;
    private Integer difficulty;
    private Integer assignScore;
    private String createDtm;
    private Integer experience;
    private List<ChallengeSubGoalDetailDto> challengeSubGoals;
    private ChallengeCardCategoryDetailDto challengeCardCategory;
}
