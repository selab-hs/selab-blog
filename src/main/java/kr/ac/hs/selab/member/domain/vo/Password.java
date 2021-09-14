package kr.ac.hs.selab.member.domain.vo;

import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.InvalidInputException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;


@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Password {
    /**
     * 최소 8자 ~ 최대 30자
     * 최소 영문자 1자
     * 최소 숫자 1자
     * 최소 특수문자 1자 :: $@$!%*#?&
     * ^(?=.*[~!@#$%^&*()_+`\-=\[\]\{\};':\",.\<>?])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\S{8,30}$
     */
    @Transient
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[가-힣A-Za-z\\d$@$!%*#?&]{8,30}$";

    @Column(name = "member_password")
    private String password;

    public Password(String password) {
        validate(password);
        this.password = password;
    }

    private Password(PasswordEncoder passwordEncoder, String password) {
        validate(password);
        this.password = passwordEncoder.encode(password);
    }

    public void validate(String password) {
        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            throw new InvalidInputException(ErrorMessage.INVALID_PASSWORD);
        }
    }

    public String getPassword() {
        return password;
    }

    public Password encode(PasswordEncoder passwordEncoder) {
        return new Password(passwordEncoder, password);
    }
}
