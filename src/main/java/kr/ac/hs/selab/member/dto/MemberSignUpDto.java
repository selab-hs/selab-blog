package kr.ac.hs.selab.member.dto;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Gender;
import kr.ac.hs.selab.member.domain.vo.Role;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class MemberSignUpDto {
    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String name;

    @NonNull
    private String nickname;

    @NonNull
    private Gender gender;

    @NonNull
    private String birth;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String studentId;

    @NonNull
    private boolean termService;

    @NonNull
    private boolean termPrivacy;

    @NonNull
    private boolean termLocation;

    protected MemberSignUpDto() {
    }

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .name(name)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .gender(gender)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .studentId(studentId)
                .termService(termService)
                .termPrivacy(termPrivacy)
                .termLocation(termLocation)
                .socialType(SocialType.BASIC)
                .role(Role.USER)
                .build();
    }
}
