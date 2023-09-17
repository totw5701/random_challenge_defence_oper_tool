package com.rcd.random_challenge_defence_oper.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    USER("ROLE_USER", "사용자"),
    MANAGER("ROLE_MANAGER", "관리자"),
    ADMIN("ROLE_ADMIN", "책임자");

    private final String key;
    private final String title;
}
