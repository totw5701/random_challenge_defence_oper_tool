package com.rcd.random_challenge_defence_oper.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPutReqDto {

    private Long id;
    private String email;
    private String nickname;
    private String picture;
    private String password;
}
