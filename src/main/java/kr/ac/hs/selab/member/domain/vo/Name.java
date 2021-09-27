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
public class Name {

    @Transient
    private static final String NAME_REGEX = "^[가-힣]{2,10}$";

    @Column(name = "member_name")
    private String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public void validate(String name) {
        if (!Pattern.matches(NAME_REGEX, name)) {
            throw new InvalidInputException(ErrorMessage.INVALID_NAME);
        }
    }

    public String getName() {
        return name;
    }

    protected Name() {

    }
}
