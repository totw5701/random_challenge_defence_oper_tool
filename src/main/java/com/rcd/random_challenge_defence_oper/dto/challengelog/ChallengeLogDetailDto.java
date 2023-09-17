package com.rcd.random_challenge_defence_oper.dto.challengelog;

import com.rcd.random_challenge_defence_oper.domain.challengelog.ChallengeLogStatus;
import com.rcd.random_challenge_defence_oper.dto.file.FileDetailDto;
import lombok.*;

import java.util.List;


@Data
@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeLogDetailDto {

    private Long id;
    private Long challengeCardId;
    private ChallengeLogStatus status;
    private String review;
    private Long memberId;
    private List<ChallengeLogSubGoalDetailDto> challengeLogSubGoalDetailDtos;
    private List<FileDetailDto> fileDetailDto;

}
