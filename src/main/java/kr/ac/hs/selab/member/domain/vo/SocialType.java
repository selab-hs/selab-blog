package kr.ac.hs.selab.member.domain.vo;

import kr.ac.hs.selab.auth.dto.GoogleAttributes;
import kr.ac.hs.selab.auth.dto.KakaoAttributes;
import kr.ac.hs.selab.auth.dto.NaverAttributes;
import kr.ac.hs.selab.auth.dto.SocialAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.function.Function;

@RequiredArgsConstructor
public enum SocialType {
    BASIC("", null),
    GOOGLE("google", GoogleAttributes::new),
    KAKAO("kakao", KakaoAttributes::new),
    NAVER("naver", NaverAttributes::new);

    private final String name;
    private final Function<OAuth2User, SocialAttributes> attribute;

    public SocialAttributes toSocialAttributes(OAuth2User oAuth2User) {
        return attribute.apply(oAuth2User);
    }

    public static SocialType of(String name) {
        return Arrays.stream(values())
                .filter(socialType -> socialType.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 입니다."));
    }
}