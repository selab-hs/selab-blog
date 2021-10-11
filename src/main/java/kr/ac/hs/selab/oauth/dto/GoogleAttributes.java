package kr.ac.hs.selab.oauth.dto;

import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Name;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class GoogleAttributes implements SocialAttributes {
    private final OAuth2User oAuth2User;

    @Override
    public SocialType socialType() {
        return SocialType.GOOGLE;
    }

    @Override
    public Name name() {
        return new Name(oAuth2User.getAttribute(AttributeKey.NAME.key));
    }

    @Override
    public Email email() {
        return new Email(oAuth2User.getAttribute(AttributeKey.EMAIL.key));
    }

    private enum AttributeKey {
        NAME("name"),
        EMAIL("email");

        private final String key;

        AttributeKey(String key) {
            this.key = key;
        }
    }
}
