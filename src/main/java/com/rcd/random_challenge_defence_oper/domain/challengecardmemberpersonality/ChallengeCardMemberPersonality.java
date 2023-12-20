package com.rcd.random_challenge_defence_oper.domain.challengecardmemberpersonality;

import com.rcd.random_challenge_defence_oper.domain.challengecard.ChallengeCard;
import com.rcd.random_challenge_defence_oper.domain.memberpersonality.MemberPersonality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChallengeCardMemberPersonality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "challenge_card_id")
    private ChallengeCard challengeCard;

    @ManyToOne
    @JoinColumn(name = "member_personality_id")
    private MemberPersonality memberPersonality;
}
