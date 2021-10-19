package kr.ac.hs.selab.member.dto;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Gender;
import kr.ac.hs.selab.member.domain.vo.Role;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import kr.ac.hs.selab.member.utils.MemberValidUtils;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class MemberSignUpDto {
    @NonNull
    @Email(regexp = MemberValidUtils.Regexp.EMAIL, message = MemberValidUtils.Message.EMAIL)
    private String email;

    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.PASSWORD, message = MemberValidUtils.Message.PASSWORD)
    private String password;

    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.NAME, message = MemberValidUtils.Message.NAME)
    private String name;

    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.NICKNAME, message = MemberValidUtils.Message.NICKNAME)
    private String nickname;

    @NonNull
    private Gender gender;

    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.BIRTH, message = MemberValidUtils.Message.BIRTH)
    private String birth;

    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.PHONE_NUMBER, message = MemberValidUtils.Message.PHONE_NUMBER)
    private String phoneNumber;

    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.STUDENT_ID, message = MemberValidUtils.Message.STUDENT_ID)
    private String studentId;

    @NonNull
    @AssertTrue
    private boolean termService;

    @NonNull
    @AssertTrue
    private boolean termPrivacy;

    @NonNull
    @AssertTrue
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
