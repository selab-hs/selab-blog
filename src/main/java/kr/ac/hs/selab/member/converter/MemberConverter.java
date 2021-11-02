package kr.ac.hs.selab.member.converter;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Role;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import kr.ac.hs.selab.member.dto.MemberBasicSignupRequest;
import kr.ac.hs.selab.oauth.dto.SocialAttributes;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    public Member toMember(MemberBasicSignupRequest request, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .gender(request.getGender())
                .birth(request.getBirth())
                .phoneNumber(request.getPhoneNumber())
                .studentId(request.getStudentId())
                .termService(request.isTermService())
                .termPrivacy(request.isTermPrivacy())
                .termLocation(request.isTermLocation())
                .socialType(SocialType.BASIC)
                .role(Role.USER)
                .build();
    }

    public Member ofSocial(SocialAttributes socialAttributes) {
        return Member.builder()
                .name(socialAttributes.name())
                .email(socialAttributes.email())
                .socialType(socialAttributes.socialType())
                .role(Role.USER)
                .build();
    }
}
