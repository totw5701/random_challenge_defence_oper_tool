package com.rcd.random_challenge_defence_oper.domain.memberbadge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {
}
