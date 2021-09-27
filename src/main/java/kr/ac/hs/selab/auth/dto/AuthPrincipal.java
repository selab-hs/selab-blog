package kr.ac.hs.selab.auth.dto;

import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.member.domain.vo.Role;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.transaction.NotSupportedException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Builder
@Getter
public class AuthPrincipal implements UserDetails, OAuth2User {
    private final Long id;
    private final Email email;
    private final Password password;
    private final SocialType socialType;
    private final Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role.grantedAuthority());
    }

    @Override
    public String getPassword() {
        return this.password.getPassword();
    }

    @Override
    public String getUsername() {
        return this.email.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @SneakyThrows
    @Deprecated
    @Override
    public Map<String, Object> getAttributes() {
        throw new NotSupportedException();
    }

    @SneakyThrows
    @Deprecated
    @Override
    public String getName() {
        throw new NotSupportedException();
    }

    public boolean isSocial() {
        return socialType != SocialType.BASIC;
    }
}
