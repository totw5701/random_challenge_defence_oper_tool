package com.rcd.random_challenge_defence_oper.domain.challengecard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeCardRepository extends JpaRepository<ChallengeCard, Long> {

}
