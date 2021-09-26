package kr.ac.hs.selab.member.dto;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Data
public class MemberSignDto {
    @NonNull
    private Email email;
    @NonNull
    private Password password;
    @NonNull
    private Name name;
    @NonNull
    private Nickname nickname;
    @NonNull
    private Gender gender;
    @NonNull
    private Birth birth;
    @NonNull
    private PhoneNumber phoneNumber;
    @NonNull
    private boolean termService;
    @NonNull
    private boolean termPrivacy;
    @NonNull
    private boolean termLocation;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .name(name)
                .password(password.encode(passwordEncoder))
                .nickname(nickname)
                .gender(gender)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .termService(termService)
                .termPrivacy(termPrivacy)
                .termLocation(termLocation)
                .role(Role.USER)
                .build();
    }
}
