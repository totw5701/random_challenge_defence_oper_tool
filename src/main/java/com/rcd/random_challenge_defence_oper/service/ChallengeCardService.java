package com.rcd.random_challenge_defence_oper.service;

import com.rcd.random_challenge_defence_oper.domain.challengecard.ChallengeCard;
import com.rcd.random_challenge_defence_oper.domain.challengecard.ChallengeCardRepository;
import com.rcd.random_challenge_defence_oper.domain.challengecardcategory.ChallengeCardCategory;
import com.rcd.random_challenge_defence_oper.domain.challengecardcategory.ChallengeCardCategoryRepository;
import com.rcd.random_challenge_defence_oper.domain.challengecardmemberpersonality.ChallengeCardMemberPersonality;
import com.rcd.random_challenge_defence_oper.domain.challengecardmemberpersonality.ChallengeCardMemberPersonalityRepository;
import com.rcd.random_challenge_defence_oper.domain.challengecardsubgoal.ChallengeCardSubGoal;
import com.rcd.random_challenge_defence_oper.domain.challengecardsubgoal.ChallengeCardSubGoalRepository;
import com.rcd.random_challenge_defence_oper.domain.memberpersonality.MemberPersonality;
import com.rcd.random_challenge_defence_oper.domain.memberpersonality.MemberPersonalityRepository;
import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeDetailDto;
import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeCardPutReqDto;
import com.rcd.random_challenge_defence_oper.dto.common.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChallengeCardService {

    private final ChallengeCardRepository challengeCardRepository;
    private final ChallengeCardSubGoalRepository challengeCardSubGoalRepository;
    private final ChallengeCardCategoryRepository challengeCardCategoryRepository;
    private final MemberPersonalityRepository memberPersonalityRepository;
    private final ChallengeCardMemberPersonalityRepository challengeCardMemberPersonalityRepository;


    public Page<ChallengeDetailDto> readPageList(Integer nowPage) {
        Pageable pageable = PageRequest.of(nowPage, 15, Sort.by("id").descending()); // 한 페이지에 15개씩 출력
        Page<ChallengeCard> challenges = challengeCardRepository.findAll(pageable);

        // dto 변환
        List<ChallengeDetailDto> challengeCardDtoList = challenges.stream()
                .map(challenge -> challenge.toDetailDto())
                .collect(Collectors.toList());

        Page<ChallengeDetailDto> challengeDtoPage = new PageImpl<>(challengeCardDtoList, challenges.getPageable(), challenges.getTotalElements());

        return challengeDtoPage;
    }

    public Page<ChallengeDetailDto> readPageListBySearch(SearchDto form, int nowPage) {
        Pageable pageable = PageRequest.of(nowPage, 15, Sort.by("id").descending());

        // 검색 조건에 따른 Specification 생성
        Specification<ChallengeCard> spec = new Specification<ChallengeCard>() {
            @Override
            public Predicate toPredicate(Root<ChallengeCard> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if("title".equals(form.getSearchType())) {
                    predicates.add(criteriaBuilder.like(root.get("title"), "%" + form.getSearch() + "%"));
                } else if ("description".equals(form.getSearchType())) {
                    predicates.add(criteriaBuilder.like(root.get("description"), "%" + form.getSearch() + "%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };

        // 검색 조건을 포함하여 데이터 조회
        Page<ChallengeCard> challenges = challengeCardRepository.findAll(spec, pageable);

        // dto 변환
        List<ChallengeDetailDto> challengeCardDtoList = challenges.stream()
                .map(challenge -> challenge.toDetailDto())
                .collect(Collectors.toList());

        Page<ChallengeDetailDto> challengeDtoPage = new PageImpl<>(challengeCardDtoList, challenges.getPageable(), challenges.getTotalElements());

        return challengeDtoPage;
    }

    public ChallengeDetailDto create(ChallengeCardPutReqDto form) {

        ChallengeCardCategory challengeCardCategory = challengeCardCategoryRepository.findById(form.getChallengeCardCategoryId()).get();

        // 챌린지 카드 생성
        ChallengeCard challengeCard = ChallengeCard.builder()
                .assignScore(form.getAssignScore())
                .title(form.getTitle())
                .difficulty(form.getDifficulty())
                .description(form.getDescription())
                .finalGoal(form.getFinalGoal())
                .createDtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .challengeCardCategory(challengeCardCategory)
                .experience(form.getExperience())
                .build();

        // 챌린지 카드 중간 도전 리스트 생성 및 할당
        List<ChallengeCardSubGoal> subGoals = form.getChallengeSubGoals().stream()
                .map(subGoalDto -> ChallengeCardSubGoal.builder()
                        .challengeCard(challengeCard)
                        .subGoal(subGoalDto)
                        .build())
                .collect(Collectors.toList());
        challengeCard.assignSubGoals(subGoals);

        ChallengeCard save = challengeCardRepository.save(challengeCard);

        return challengeCard.toDetailDto();
    }

    public ChallengeDetailDto readOne(Long id){
        return findById(id).toDetailDto();
    }

    public ChallengeCard findById(Long id) {
        Optional<ChallengeCard> opChallenge = challengeCardRepository.findById(id);
        if(!opChallenge.isPresent()) {
            log.error("challengeCard is not exist");
        }
        return opChallenge.get();
    }

    public ChallengeCard update(ChallengeCardPutReqDto form) {
        ChallengeCard challenge = findById(form.getId());
        challenge.update(form);

        // 챌린지 카테고리 업데이트
        if(form.getChallengeCardCategoryId() != null) {
            Optional<ChallengeCardCategory> opChallengeCardCategory = challengeCardCategoryRepository.findById(form.getChallengeCardCategoryId());
            if(!opChallengeCardCategory.isPresent()) {
                log.error("존재하지 않는 챌린지 카테고리입니다.");
            }
            challenge.updateChallengeCardCategory(opChallengeCardCategory.get());
        }

        // 챌린지 카드 중간목표 수정
        if(form.getChallengeSubGoals() != null) {
            List<ChallengeCardSubGoal> oldChallengeCardSubGoals = challenge.getChallengeCardSubGoals();

            List<ChallengeCardSubGoal> newChallengeCardSubGoals = form.getChallengeSubGoals().stream()
                    .map(subGoalDto -> ChallengeCardSubGoal.builder()
                            .challengeCard(challenge)
                            .subGoal(subGoalDto)
                            .build())
                    .collect(Collectors.toList());

            // 이전 중간 목표 삭제
            for(ChallengeCardSubGoal goal : oldChallengeCardSubGoals){
                challengeCardSubGoalRepository.delete(goal);
            }

            challenge.assignSubGoals(newChallengeCardSubGoals);
        }

        return challenge;
    }

    public void delete(Long id) {
        ChallengeCard challengeCard = findById(id);
        challengeCardRepository.delete(challengeCard);
    }

    public void updateChallengeCard(ChallengeCardPutReqDto form) throws Exception {
        // 기존 사용자 특성 삭제
        List<ChallengeCardMemberPersonality> selectedPersonalities = challengeCardMemberPersonalityRepository.findAllByChallengeCardId(form.getId());
        challengeCardMemberPersonalityRepository.deleteAll(selectedPersonalities);

        // 기존 챌린지 중간목표 삭제
        List<ChallengeCardSubGoal> challengeCardSubGoals = challengeCardSubGoalRepository.findAllByChallengeCardId(form.getId());
        for(ChallengeCardSubGoal challengeCardSubGoal : challengeCardSubGoals) {
            challengeCardSubGoalRepository.deleteById(challengeCardSubGoal.getId());
        }

        Optional<ChallengeCard> challengeCardOp = challengeCardRepository.findById(form.getId());
        if(!challengeCardOp.isPresent()) {
            log.error("존재하지 않는 챌린지 카드입니다.");
            throw new Exception("존재하지 않는 챌린지 카드입니다.");
        }
        ChallengeCard challengeCard = challengeCardOp.get();

        // 신규 사용자 특성 삽입
        List<Long> personalityList = form.getMemberPersonalityList();
        if(personalityList != null) {
            personalityList.forEach(id -> {
                MemberPersonality memberPersonality = memberPersonalityRepository.findById(id).get();
                ChallengeCardMemberPersonality challengeCardMemberPersonality = ChallengeCardMemberPersonality.builder()
                        .memberPersonality(memberPersonality)
                        .challengeCard(challengeCard)
                        .build();
                challengeCardMemberPersonalityRepository.save(challengeCardMemberPersonality);
            });
        }

        // 신규 챌린지 중간목표 삽입
        List<String> challengeSubGoals = form.getChallengeSubGoals();
        if(challengeSubGoals != null) {
            challengeSubGoals.forEach(subGoal -> {
                ChallengeCardSubGoal challengeCardSubGoal = ChallengeCardSubGoal.builder()
                        .challengeCard(challengeCard)
                        .subGoal(subGoal)
                        .build();
                challengeCardSubGoalRepository.save(challengeCardSubGoal);
            });
        }

        // 챌린지 카드 데이터 업데이트
        challengeCard.update(form);

        // 챌린지 카테고리 업데이트
        ChallengeCardCategory challengeCardCategory = challengeCardCategoryRepository.findById(form.getChallengeCardCategoryId()).get();
        challengeCard.updateChallengeCardCategory(challengeCardCategory);
    }

    public ChallengeCard createChallengeCard(ChallengeCardPutReqDto form) throws Exception {

        Optional<ChallengeCardCategory> challengeCategoryOp = challengeCardCategoryRepository.findById(form.getChallengeCardCategoryId());
        if(!challengeCategoryOp.isPresent()) {
            throw new Exception("not found challengeCategory. id:" + form.getChallengeCardCategoryId());
        }

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        ChallengeCard challengeCard = ChallengeCard.builder()
                .title(form.getTitle())
                .description(form.getDescription())
                .finalGoal(form.getFinalGoal())
                .difficulty(form.getDifficulty())
                .challengeCardCategory(challengeCategoryOp.get())
                .assignScore(form.getAssignScore())
                .createDtm(now)
                .experience(form.getExperience())
                .build();
        challengeCardRepository.save(challengeCard);

        form.getChallengeSubGoals().forEach(subGoal -> {
            ChallengeCardSubGoal challengeSubGoal = ChallengeCardSubGoal.builder()
                    .subGoal(subGoal)
                    .challengeCard(challengeCard)
                    .build();
            challengeCardSubGoalRepository.save(challengeSubGoal);
        });


        List<MemberPersonality> personalities = memberPersonalityRepository.findAllById(form.getMemberPersonalityList());
        personalities.forEach(personality -> {
            ChallengeCardMemberPersonality challengeCardMemberPersonality = ChallengeCardMemberPersonality.builder()
                    .challengeCard(challengeCard)
                    .memberPersonality(personality)
                    .build();
            challengeCardMemberPersonalityRepository.save(challengeCardMemberPersonality);
        });

        return challengeCard;
    }


}
