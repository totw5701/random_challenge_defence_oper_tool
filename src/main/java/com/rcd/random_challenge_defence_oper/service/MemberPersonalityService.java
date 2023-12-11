package com.rcd.random_challenge_defence_oper.service;

import com.rcd.random_challenge_defence_oper.domain.memberpersonality.MemberPersonality;
import com.rcd.random_challenge_defence_oper.domain.memberpersonality.MemberPersonalityRepository;
import com.rcd.random_challenge_defence_oper.dto.memberpersonality.MemberPersonalityCreateReqDto;
import com.rcd.random_challenge_defence_oper.dto.memberpersonality.MemberPersonalityDetailDto;
import com.rcd.random_challenge_defence_oper.dto.memberpersonality.MemberPersonalityUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberPersonalityService {

    private final MemberPersonalityRepository memberPersonalityRepository;

    @Transactional
    public MemberPersonalityDetailDto create(MemberPersonalityCreateReqDto form) {

        MemberPersonality memberPersonality = MemberPersonality.builder()
                .title(form.getTitle())
                .description(form.getDescription())
                .build();

        MemberPersonality save = memberPersonalityRepository.save(memberPersonality);
        return save.toDetailDto();
    }

    @Transactional
    public MemberPersonalityDetailDto update(MemberPersonalityUpdateReqDto form) {
        Optional<MemberPersonality> byId = memberPersonalityRepository.findById(form.getId());
        if(!byId.isPresent()) {
            //error 처리
        }

        byId.get().update(form.getTitle(), form.getDescription());

        return byId.get().toDetailDto();
    }

    public MemberPersonalityDetailDto delete(Long id) {
        Optional<MemberPersonality> byId = memberPersonalityRepository.findById(id);
        if(!byId.isPresent()) {
            //error 처리
        }
        memberPersonalityRepository.delete(byId.get());
        return byId.get().toDetailDto();
    }

    public MemberPersonality readOne(Long id) {
        Optional<MemberPersonality> byId = memberPersonalityRepository.findById(id);
        if(!byId.isPresent()) {
            //error 처리
        }
        return byId.get();
    }

    public List<MemberPersonality> findAll() {
        return memberPersonalityRepository.findAll();
    }

    public List<MemberPersonality> findAllByChallengeCardId(Long id) {
        return memberPersonalityRepository.findAllByChallengeCardId(id);
    }

    public List<MemberPersonality> findAllById(List<Long> ids) {
        return memberPersonalityRepository.findAllById(ids);
    }
}
