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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Birth {
    /**
     * yyyymmdd :: 19970908
     */
    @Transient
    private final static String BIRTH_REGEX = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";

    @Column(name = "member_birth")
    private String birth;

    public Birth(String birth) {
        validate(birth);
        this.birth = birth;
    }

    public void validate(String birth) {
        if (!Pattern.matches(BIRTH_REGEX, birth)) {
            throw new InvalidInputException(ErrorMessage.INVALID_BIRTH);
        }
    }

    public String getBirth() {
        return birth;
    }
}

