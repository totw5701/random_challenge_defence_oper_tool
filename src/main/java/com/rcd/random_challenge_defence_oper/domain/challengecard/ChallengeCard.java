package com.rcd.random_challenge_defence_oper.domain.challengecard;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.rcd.random_challenge_defence_oper.domain.challengecardcategory.ChallengeCardCategory;
import com.rcd.random_challenge_defence_oper.domain.challengecardmemberpersonality.ChallengeCardMemberPersonality;
import com.rcd.random_challenge_defence_oper.domain.challengecardsubgoal.ChallengeCardSubGoal;
import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeDetailDto;
import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeCardPutReqDto;
import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeSubGoalDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Challenge 객체를 JSON으로 바꿀때 다른 객체에게 참조 받는 경우 ID 만 넘긴다.
public class ChallengeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChallengeCardCategory challengeCardCategory;

    @Column(unique = true)
    private String title;
    private String description;
    private String finalGoal;
    private Integer difficulty;
    private Integer assignScore;
    private Integer experience;

    private String createDtm;

    @OneToMany(mappedBy = "challengeCard", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChallengeCardSubGoal> challengeCardSubGoals;

    @OneToMany(mappedBy = "challengeCard", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChallengeCardMemberPersonality> challengeCardMemberPersonalities;

    public void update(ChallengeCardPutReqDto form) {
        this.title = (form.getTitle() != null) ? form.getTitle() : this.title;
        this.description = (form.getDescription() != null) ? form.getDescription() : this.description;
        this.finalGoal = (form.getFinalGoal() != null) ? form.getFinalGoal() : this.finalGoal;
        this.difficulty = (form.getDifficulty() != null) ? form.getDifficulty() : this.difficulty;
        this.assignScore = (form.getAssignScore() != null) ? form.getAssignScore() : this.assignScore;
        this.experience = (form.getExperience() != null) ? form.getExperience() : this.experience;
    }


    public void updateChallengeCardCategory(ChallengeCardCategory challengeCardCategory) {
        this.challengeCardCategory = challengeCardCategory;
    }

    public ChallengeDetailDto toDetailDto() {
        List<ChallengeSubGoalDetailDto> subGoals = new ArrayList<>();
        if(this.challengeCardSubGoals != null) {
            subGoals = challengeCardSubGoals.stream().map((challengeSubGoal -> challengeSubGoal.toDto())).collect(Collectors.toList());
        }
        return ChallengeDetailDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .finalGoal(this.finalGoal)
                .difficulty(this.difficulty)
                .assignScore(this.assignScore)
                .createDtm(this.createDtm)
                .experience(this.experience)
                .challengeSubGoals(subGoals)
                .challengeCardCategory(this.challengeCardCategory.toDetailDto())
                .build();
    }

    public void assignSubGoals(List<ChallengeCardSubGoal> subGoals){
        this.challengeCardSubGoals = subGoals;
    }
}
