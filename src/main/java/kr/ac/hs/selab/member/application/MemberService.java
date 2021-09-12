package kr.ac.hs.selab.member.application;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.dro.MemberSignDto;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createMember(MemberSignDto request) {
        memberRepository.save(request.toMember(passwordEncoder));
    }

    private Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("회원 정보가 없습니다.");
                });
    }
}
