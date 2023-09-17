package com.rcd.random_challenge_defence_oper.domain.challengelog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChallengeLogStatus {

    READY("R"),
    SUCCESS("S"),
    ACTION("A"),
    PAUSE("P");

    private final String code;

}
