package com.rcd.random_challenge_defence_oper.domain.challengecardcategory;

import com.rcd.random_challenge_defence_oper.dto.challengeCategory.ChallengeCardCategoryDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChallengeCardCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    public ChallengeCardCategoryDetailDto toDetailDto() {
        return ChallengeCardCategoryDetailDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .build();
    }
}
