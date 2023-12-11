package com.rcd.random_challenge_defence_oper.domain.memberpersonality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPersonalityRepository extends JpaRepository<MemberPersonality, Long> {

    @Query("select ccmp.memberPersonality from ChallengeCardMemberPersonality ccmp where ccmp.challengeCard.id = :id")
    List<MemberPersonality> findAllByChallengeCardId(@Param("id") Long id);
}
