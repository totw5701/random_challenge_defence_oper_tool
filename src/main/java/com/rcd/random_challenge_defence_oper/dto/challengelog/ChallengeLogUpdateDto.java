package com.rcd.random_challenge_defence_oper.dto.challengelog;

import com.rcd.random_challenge_defence_oper.domain.challengelog.ChallengeLogStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Builder
@Getter
public class ChallengeLogUpdateDto {

    private Long id;
    private String evidence;
    private ChallengeLogStatus status;
    private String review;
    private Long image;
}
