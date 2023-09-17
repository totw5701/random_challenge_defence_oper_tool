package com.rcd.random_challenge_defence_oper.dto.member;

import com.rcd.random_challenge_defence_oper.domain.member.MemberRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDetailsDto {

    private Long id;
    private String email;
    private String nickname;
    private MemberRole memberRole;
    private String picture;
    private String joinDtm;
    private String modifyDtm;
    private Long experience;

}
