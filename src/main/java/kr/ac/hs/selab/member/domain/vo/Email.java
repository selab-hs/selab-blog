package kr.ac.hs.selab.member.domain.vo;

import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.InvalidInputException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    /**
     * wrjs@naver.com
     */
    @Transient
    private static final String EMAIL_REGEX = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@" + "[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,3})$";

    @Column(name = "MEMBER_EMAIL", nullable = false)
    private String email;


    public Email(String email) {
        validate(email);
        this.email = email;
    }

    public void validate(String email) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new InvalidInputException(ErrorMessage.INVALID_EMAIL);
        }
    }

    public String getEmail() {
        return email;
    }
}
