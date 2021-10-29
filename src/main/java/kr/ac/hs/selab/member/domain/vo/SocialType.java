package kr.ac.hs.selab.member.domain.vo;

import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.OAuth2LoginException;
import kr.ac.hs.selab.oauth.dto.GoogleAttributes;
import kr.ac.hs.selab.oauth.dto.KakaoAttributes;
import kr.ac.hs.selab.oauth.dto.NaverAttributes;
import kr.ac.hs.selab.oauth.dto.SocialAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.function.Function;

@RequiredArgsConstructor
public enum SocialType {
    BASIC("basic", null),
    GOOGLE("google", GoogleAttributes::new),
    KAKAO("kakao", KakaoAttributes::new),
    NAVER("naver", NaverAttributes::new);

    private final String registrationId;
    private final Function<OAuth2User, SocialAttributes> newSocialMemberFunction;

    public SocialAttributes toSocialAttributes(OAuth2User oAuth2User) {
        return newSocialMemberFunction.apply(oAuth2User);
    }

    public static SocialType of(String registrationId) {
        return Arrays.stream(values())
                .filter(socialType -> socialType.registrationId.equals(registrationId))
                .findFirst()
                .orElseThrow(() -> new OAuth2LoginException(ErrorMessage.NOT_SUPPORTED_SOCIAL_LOGIN));
    }
}