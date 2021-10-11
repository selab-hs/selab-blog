package kr.ac.hs.selab.auth.application;

import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return memberRepository.findByEmail(new Email(username))
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
                })
                .toCustomUserDetails();
    }
}
