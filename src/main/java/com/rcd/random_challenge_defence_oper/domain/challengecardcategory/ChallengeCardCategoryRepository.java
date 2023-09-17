package com.rcd.random_challenge_defence_oper.domain.challengecardcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeCardCategoryRepository extends JpaRepository<ChallengeCardCategory, Long> {
}
