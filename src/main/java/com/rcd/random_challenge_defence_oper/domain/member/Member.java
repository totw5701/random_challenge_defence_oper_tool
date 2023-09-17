package com.rcd.random_challenge_defence_oper.domain.member;

import com.rcd.random_challenge_defence_oper.dto.member.MemberDetailsDto;
import com.rcd.random_challenge_defence_oper.dto.member.MemberPutReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Column(unique = true)
    private String email;
    private String password;
    private String nickname;
    private String picture;
    private String joinDtm;
    private String modifyDtm;
    private Long experience;

    public Member entityUpdate(String picture) {
        this.picture = picture;
        return this;
    }

    public MemberDetailsDto toDetailDto() {
        return MemberDetailsDto.builder()
                .id(this.id)
                .email(this.email)
                .nickname(this.nickname)
                .memberRole(this.memberRole)
                .joinDtm(this.joinDtm)
                .experience(this.experience)
                .modifyDtm(this.modifyDtm)
                .build();
    }

    public Member update(MemberPutReqDto form) {
        if(form.getNickname() != null) {
            this.nickname = form.getNickname();
        }
        if(form.getPicture() != null) {
            this.picture = form.getPicture();
        }
        if(form.getPassword() != null) {
            this.password = form.getPassword();
        }
        return this;
    }

    public void increaseExperience(Integer experience) {
        this.experience += experience;
    }
}
