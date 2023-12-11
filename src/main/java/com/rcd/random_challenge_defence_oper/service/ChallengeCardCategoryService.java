package com.rcd.random_challenge_defence_oper.service;

import com.rcd.random_challenge_defence_oper.domain.challengecardcategory.ChallengeCardCategory;
import com.rcd.random_challenge_defence_oper.domain.challengecardcategory.ChallengeCardCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChallengeCardCategoryService {

    private final ChallengeCardCategoryRepository challengeCardCategoryRepository;

    public ChallengeCardCategory findById(Long id) {
        Optional<ChallengeCardCategory> byId = challengeCardCategoryRepository.findById(id);
        if(!byId.isPresent()) {
            // 에러 로그 작성
            return null;
        }
        return byId.get();
    }

    public List<ChallengeCardCategory> findAllToDetail() {
        return challengeCardCategoryRepository.findAll();
    }

}
