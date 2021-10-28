package kr.ac.hs.selab.auth.application;

import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.InvalidLoginException;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .orElseThrow(() -> new InvalidLoginException(ErrorMessage.NON_EXISTENT_USER))
                .toCustomUserDetails();
    }
}
