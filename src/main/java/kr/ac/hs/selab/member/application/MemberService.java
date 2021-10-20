package kr.ac.hs.selab.member.application;

import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.InvalidLoginException;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.dto.MemberPrivacyDto;
import kr.ac.hs.selab.member.dto.MemberSignUpDto;
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
    public void createMember(MemberSignUpDto memberSignUpDto) {
        memberRepository.save(memberSignUpDto.toMember(passwordEncoder));
    }

    private Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> {
                    throw new InvalidLoginException(ErrorMessage.NON_EXISTENT_USER);
                });
    }

    @Transactional
    public void updateSocialMember(Long memberId, MemberPrivacyDto memberPrivacyDto) {
        Member member = findById(memberId);

        if (!member.isNotCompletedSingUp()) {
            throw new InvalidLoginException(ErrorMessage.EXISTENT_USER);
        }

        validateDuplicateMemberPrivacy(
                memberPrivacyDto.getNickname(),
                memberPrivacyDto.getPhoneNumber()
        );

        member.updateMemberPrivacy(memberPrivacyDto);
    }

    @Transactional
    public void updateMember(Long memberId, MemberPrivacyDto memberPrivacyDto) {
        Member member = findById(memberId);
        member.updateMemberPrivacy(memberPrivacyDto);
    }

    private void validateDuplicateMemberPrivacy(String nickname, String phoneNumber) {
        if (existsByNickname(nickname)) {
            throw new InvalidLoginException(ErrorMessage.EXISTENT_NICKNAME);
        }
        if (existsByPhoneNumber(phoneNumber)) {
            throw new InvalidLoginException(ErrorMessage.EXISTENT_PHONE_NUMBER);
        }
    }

    @Transactional(readOnly = true)
    public boolean existsByNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(String phoneNumber) {
        return memberRepository.existsByPhoneNumber(phoneNumber);
    }
}
