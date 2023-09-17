package com.rcd.random_challenge_defence_oper.domain.memberpersonality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPersonalityRepository extends JpaRepository<MemberPersonality, Long> {
}
