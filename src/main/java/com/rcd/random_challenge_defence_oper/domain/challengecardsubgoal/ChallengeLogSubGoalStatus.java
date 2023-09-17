package com.rcd.random_challenge_defence_oper.domain.challengecardsubgoal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChallengeLogSubGoalStatus {

    READY("R"),
    SUCCESS("S");

    private final String code;
}
