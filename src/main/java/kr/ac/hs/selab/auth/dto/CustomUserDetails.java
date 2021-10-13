package kr.ac.hs.selab.auth.dto;

import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.member.domain.vo.Role;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {
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
        return password.getPassword();
    }

    @Override
    public String getUsername() {
        return email.getEmail();
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
}
