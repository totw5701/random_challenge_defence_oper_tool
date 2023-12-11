package com.rcd.random_challenge_defence_oper.controller;

import com.rcd.random_challenge_defence_oper.domain.challengecard.ChallengeCard;
import com.rcd.random_challenge_defence_oper.domain.challengecardcategory.ChallengeCardCategory;
import com.rcd.random_challenge_defence_oper.domain.memberpersonality.MemberPersonality;
import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeCardPutReqDto;
import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeSubGoalDetailDto;
import com.rcd.random_challenge_defence_oper.dto.challengeCategory.ChallengeCardCategoryDetailDto;
import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeDetailDto;
import com.rcd.random_challenge_defence_oper.dto.common.SearchDto;
import com.rcd.random_challenge_defence_oper.dto.memberpersonality.MemberPersonalityDetailDto;
import com.rcd.random_challenge_defence_oper.service.ChallengeCardCategoryService;
import com.rcd.random_challenge_defence_oper.service.ChallengeCardService;
import com.rcd.random_challenge_defence_oper.service.MemberPersonalityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class challengeCardController {

    private final ChallengeCardService challengeCardService;
    private final ChallengeCardCategoryService challengeCardCategoryService;
    private final MemberPersonalityService memberPersonalityService;

    @GetMapping(value = {"/challenge-card"})
    public String list(SearchDto form, Model model, @RequestParam Optional<Integer> page) {
        int nowPage = 0;
        if(page.isPresent()) {
            nowPage = page.get();
        }

        Page<ChallengeDetailDto> challengeDetailDtos;
        if(StringUtils.isBlank(form.getSearch())) {
            challengeDetailDtos = challengeCardService.readPageList(nowPage);
        } else {
            challengeDetailDtos = challengeCardService.readPageListBySearch(form, nowPage);
        }
        model.addAttribute("challengeCards", challengeDetailDtos);
        return "challenge-card";
    }

    @GetMapping("/challenge-card/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ChallengeCard card = challengeCardService.findById(id);
        List<ChallengeCardCategory> categoryEntities = challengeCardCategoryService.findAllToDetail();
        List<ChallengeCardCategoryDetailDto> categories = categoryEntities.stream().map(entity -> entity.toDetailDto()).collect(Collectors.toList());

        List<MemberPersonality> personalityEntities = memberPersonalityService.findAll();
        List<MemberPersonalityDetailDto> allPersonalities = personalityEntities.stream().map(entity -> entity.toDetailDto()).collect(Collectors.toList());

        List<MemberPersonality> selectedPersonalityEntities = memberPersonalityService.findAllByChallengeCardId(card.getId());
        List<MemberPersonalityDetailDto> selectedPersonalities = selectedPersonalityEntities.stream().map(entity -> entity.toDetailDto()).collect(Collectors.toList());

        model.addAttribute("selectedPersonalities", selectedPersonalities);
        model.addAttribute("allPersonalities", allPersonalities);
        model.addAttribute("card", card.toDetailDto());
        model.addAttribute("categories", categories);
        return "challenge-card-detail";
    }

    @GetMapping("/challenge-card/create")
    public String createPage(Model model) {

        List<ChallengeCardCategory> categoryEntities = challengeCardCategoryService.findAllToDetail();
        List<ChallengeCardCategoryDetailDto> categories = categoryEntities.stream().map(entity -> entity.toDetailDto()).collect(Collectors.toList());

        List<MemberPersonality> personalityEntities = memberPersonalityService.findAll();
        List<MemberPersonalityDetailDto> allPersonalities = personalityEntities.stream().map(entity -> entity.toDetailDto()).collect(Collectors.toList());

        List<ChallengeSubGoalDetailDto> challengeSubGoalDetailDtoList = new ArrayList();
        ChallengeSubGoalDetailDto challengeSubGoalDetailDto = ChallengeSubGoalDetailDto.builder()
                .build();
        challengeSubGoalDetailDtoList.add(challengeSubGoalDetailDto);

        ChallengeDetailDto challengeDetailDto = ChallengeDetailDto.builder()
                .challengeSubGoals(challengeSubGoalDetailDtoList)
                .assignScore(0)
                .experience(0)
                .difficulty(1)
                .build();

        model.addAttribute("allPersonalities", allPersonalities);
        model.addAttribute("card", challengeDetailDto);
        model.addAttribute("categories", categories);

        return "challenge-card-create";
    }

    @PostMapping("/challenge-card/create")
    public String create(ChallengeCardPutReqDto form) throws Exception {
        ChallengeCard challengeCard = challengeCardService.createChallengeCard(form);
        return "redirect:/challenge-card/" + challengeCard.getId();
    }

    @PostMapping("/challenge-card/update")
    public String update(ChallengeCardPutReqDto form) throws Exception {
        challengeCardService.updateChallengeCard(form);
        return "redirect:/challenge-card/" + form.getId();
    }

    @PostMapping("/challenge-card/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        challengeCardService.delete(id);
        return "redirect:/challenge-card";
    }
}
