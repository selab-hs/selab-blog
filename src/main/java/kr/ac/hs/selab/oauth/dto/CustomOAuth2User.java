package kr.ac.hs.selab.oauth.dto;

import kr.ac.hs.selab.member.domain.vo.Role;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.transaction.NotSupportedException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Builder
public class CustomOAuth2User implements OAuth2User {
    private final Long id;
    private final String email;
    private final SocialType socialType;
    private final Role role;

    public Long getId() {
        return id;
    }

    @SneakyThrows
    @Deprecated
    @Override
    public Map<String, Object> getAttributes() {
        throw new NotSupportedException();
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role.grantedAuthority());
    }
}
