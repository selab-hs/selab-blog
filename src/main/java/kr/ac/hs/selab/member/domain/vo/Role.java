package kr.ac.hs.selab.member.domain.vo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public enum Role {
    GUEST(new SimpleGrantedAuthority("ROLE_GUEST")),
    USER(new SimpleGrantedAuthority("ROLE_USER")),
    ADMIN(new SimpleGrantedAuthority("ROLE_ADMIN"));

    private final GrantedAuthority grantedAuthority;

    public GrantedAuthority grantedAuthority() {
        return grantedAuthority;
    }
}
