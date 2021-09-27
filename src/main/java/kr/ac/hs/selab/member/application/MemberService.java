package kr.ac.hs.selab.member.application;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Nickname;
import kr.ac.hs.selab.member.domain.vo.PhoneNumber;
import kr.ac.hs.selab.member.dto.MemberSignDto;
import kr.ac.hs.selab.member.dto.SocialMemberSignDto;
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

    @Transactional
    public void updateSocialMemberInfo(long memberId, SocialMemberSignDto socialMemberSign) {
        Member member = findById(memberId);

        if (!member.isNotCompletedSingUp()) {
            throw new RuntimeException("가입이 이미 완료 되었습니다.");
        }
        validateDuplicateMemberPrivacy(
                socialMemberSign.getNickname(),
                socialMemberSign.getPhoneNumber()
        );

        member.updateSocialMemberInfo(socialMemberSign);
    }

    private void validateDuplicateMemberPrivacy(Nickname nickname, PhoneNumber phoneNumber) {
        if (existsByNickname(nickname)) {
            throw new RuntimeException("중복된 닉네임 입니다.");
        }
        if (existsByPhoneNumber(phoneNumber)) {
            throw new RuntimeException("중복된 전화번호 입니다.");
        }
    }

    @Transactional(readOnly = true)
    public boolean existsByNickname(Nickname nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return memberRepository.existsByPhoneNumber(phoneNumber);
    }
}
