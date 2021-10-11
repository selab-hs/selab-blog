package kr.ac.hs.selab.auth.application;

import kr.ac.hs.selab.auth.dto.SocialAttributes;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(final OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {
        SocialAttributes socialAttributes = newSocialAttributes(userRequest);
        Member member = findAndSaveSocialMember(socialAttributes);

        if (!member.isSocial()) {
            throw new RuntimeException("지원하지 않는 소셜 로그인 입니다.");
        }
        return member.toCustomOAuth2User();
    }

    @Transactional
    public Member findAndSaveSocialMember(SocialAttributes socialAttributes) {
        return memberRepository.findByEmail(socialAttributes.email())
                .orElseGet(() ->
                        memberRepository.save(Member.ofSocial(socialAttributes))
                );
    }

    private SocialAttributes newSocialAttributes(final OAuth2UserRequest userRequest) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        return SocialType.of(registrationId)
                .toSocialAttributes(oAuth2User);
    }
}
