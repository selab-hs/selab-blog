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
    public void create(MemberSignUpDto memberSignUpDto) {
        memberRepository.save(memberSignUpDto.toMember(passwordEncoder));
    }

    @Transactional
    public void updateSocialInfo(Long memberId, MemberPrivacyDto memberPrivacyDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new InvalidLoginException(ErrorMessage.NON_EXISTENT_USER));

        validateDuplicateNickname(memberPrivacyDto.getNickname());
        validateDuplicatePhoneNumber(memberPrivacyDto.getPhoneNumber());
        validatePrivacyEmpty(member);

        member.updatePrivacy(memberPrivacyDto);
    }

    @Transactional
    public void updatePrivacy(Long memberId, MemberPrivacyDto memberPrivacyDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new InvalidLoginException(ErrorMessage.NON_EXISTENT_USER));

        validateDuplicateNickname(memberPrivacyDto.getNickname());
        validateDuplicatePhoneNumber(memberPrivacyDto.getPhoneNumber());

        member.updatePrivacy(memberPrivacyDto);
    }

    private void validatePrivacyEmpty(Member member) {
        if (!member.checkPrivacyEmpty()) {
            throw new InvalidLoginException(ErrorMessage.EXISTENT_USER);
        }
    }

    private void validateDuplicateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new InvalidLoginException(ErrorMessage.EXISTENT_NICKNAME);
        }
    }

    private void validateDuplicatePhoneNumber(String phoneNumber) {
        if (memberRepository.existsByPhoneNumber(phoneNumber)) {
            throw new InvalidLoginException(ErrorMessage.EXISTENT_PHONE_NUMBER);
        }
    }
}