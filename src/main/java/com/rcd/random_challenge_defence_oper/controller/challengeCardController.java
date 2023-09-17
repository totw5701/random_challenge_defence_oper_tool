package com.rcd.random_challenge_defence_oper.controller;

import com.rcd.random_challenge_defence_oper.dto.challengeCard.ChallengeDetailDto;
import com.rcd.random_challenge_defence_oper.service.ChallengeCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class challengeCardController {

    private final ChallengeCardService challengeCardService;

    @GetMapping(value = {"/challenge-card", "/challenge-card/{page}"})
    public String list(HttpServletRequest req, HttpServletResponse res,
                       Model model, @PathVariable Optional<Integer> page) {
        int nowPage = 0;
        if(page.isPresent()) {
            nowPage = page.get();
        }

        Page<ChallengeDetailDto> challengeDetailDtos = challengeCardService.readPageList(nowPage);
        model.addAttribute("challengeCards", challengeDetailDtos);
        return "challenge-card";
    }
}
