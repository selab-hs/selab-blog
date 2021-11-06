package kr.ac.hs.selab.oauth.dto;

import kr.ac.hs.selab.member.domain.vo.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoAttributes implements SocialAttributes {
    private final OAuth2User oAuth2User;

    @Override
    public SocialType socialType() {
        return SocialType.KAKAO;
    }

    private Object account(String key) {
        Map<String, Object> account = oAuth2User.getAttribute(
                AttributeKey.KAKAO_ACCOUNT.key
        );
        //noinspection ConstantConditions
        return account.get(key);
    }

    @Override
    public String name() {
        //noinspection unchecked
        Map<String, Object> profile = (Map<String, Object>) account(AttributeKey.PROFILE.key);
        return (String) profile.get(AttributeKey.NICKNAME.key);
    }

    @Override
    public String email() {
        return (String) account(AttributeKey.EMAIL.key);
    }

    private enum AttributeKey {
        KAKAO_ACCOUNT("kakao_account"),
        EMAIL("email"),
        PROFILE("profile"),
        NICKNAME("nickname");

        private final String key;

        AttributeKey(String key) {
            this.key = key;
        }
    }
}
