package kr.ac.hs.selab.auth.application;

import kr.ac.hs.selab.auth.dto.SocialAttributes;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService,
        OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(new Email(username))
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
                })
                .toAuthPrincipal();
    }

    @Transactional
    @Override
    public OAuth2User loadUser(final OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        SocialAttributes socialAttributes = newSocialAttributes(userRequest);
        Member member = findAndSaveSocialMember(socialAttributes);

        if (!member.isSocial()) {
            throw new RuntimeException("지원하지 않는 소셜 로그인 입니다.");
        }
        return member.toAuthPrincipal();
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
        SocialType socialType = SocialType.of(registrationId);
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        return socialType.toSocialAttributes(oAuth2User);
    }
}
