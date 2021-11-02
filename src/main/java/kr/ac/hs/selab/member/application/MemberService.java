package kr.ac.hs.selab.member.application;

import kr.ac.hs.selab.exception.BasicAuthLoginException;
import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.OAuth2LoginException;
import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.dto.MemberBasicSignupRequest;
import kr.ac.hs.selab.member.dto.MemberPrivacyRequest;
import kr.ac.hs.selab.member.dto.MemberSocialSignupRequest;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(MemberBasicSignupRequest request) {
        memberRepository.save(memberConverter.toMember(request, passwordEncoder));
    }

    @Transactional
    public void updateSocialInfo(Long memberId, MemberSocialSignupRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new OAuth2LoginException(ErrorMessage.NON_EXISTENT_USER));

        validateDuplicateNickname(request.getNickname());
        validateDuplicatePhoneNumber(request.getPhoneNumber());
        validatePrivacyEmpty(member);

        member.updateSocial(request);
    }

    @Transactional
    public void updatePrivacy(Long memberId, MemberPrivacyRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BasicAuthLoginException(ErrorMessage.NON_EXISTENT_USER));

        validateDuplicateNickname(request.getNickname());
        validateDuplicatePhoneNumber(request.getPhoneNumber());

        member.updatePrivacy(request);
    }

    private void validatePrivacyEmpty(Member member) {
        if (!member.checkPrivacyEmpty()) {
            throw new BasicAuthLoginException(ErrorMessage.EXISTENT_USER);
        }
    }

    private void validateDuplicateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new BasicAuthLoginException(ErrorMessage.EXISTENT_NICKNAME);
        }
    }

    private void validateDuplicatePhoneNumber(String phoneNumber) {
        if (memberRepository.existsByPhoneNumber(phoneNumber)) {
            throw new BasicAuthLoginException(ErrorMessage.EXISTENT_PHONE_NUMBER);
        }
    }
}