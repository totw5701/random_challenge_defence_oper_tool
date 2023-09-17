package com.rcd.random_challenge_defence_oper.domain.membermemberpersonality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMemberPersonalityRepository extends JpaRepository<MemberMemberPersonality, Long> {
}
