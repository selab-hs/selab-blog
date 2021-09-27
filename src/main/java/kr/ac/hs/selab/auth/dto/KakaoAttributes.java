package kr.ac.hs.selab.auth.dto;

import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Name;
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

    @Override
    public String userKey() {
        return oAuth2User.getName();
    }

    @Override
    public Name name() {
        //noinspection unchecked
        Map<String, Object> profile = (Map<String, Object>) account(AttributeKey.PROFILE.key);
        return new Name(
                (String) profile.get(
                        AttributeKey.NICKNAME.key
                )
        );
    }

    @Override
    public Email email() {
        return new Email(
                (String) account(
                        AttributeKey.EMAIL.key
                )
        );
    }

    private Object account(String key) {
        Map<String, Object> account = oAuth2User.getAttribute(
                AttributeKey.KAKAO_ACCOUNT.key
        );
        //noinspection ConstantConditions
        return account.get(key);
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
