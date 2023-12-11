package com.rcd.random_challenge_defence_oper.domain.challengecardmemberpersonality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeCardMemberPersonalityRepository extends JpaRepository<ChallengeCardMemberPersonality, Long> {
    List<ChallengeCardMemberPersonality> findAllByChallengeCardId(Long id);
}
