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
public class ClassNumber {

    @Transient
    private static final String CLASS_NUMBER_REGEX = "^[0-9]{4}58[0-9]{3}$";

    @Column(name = "member_class_number")
    private String classNumber;

    public ClassNumber(String classNumber) {
        validate(classNumber);
        this.classNumber = classNumber;
    }

    public void validate(String classNumber) {
        if (!Pattern.matches(CLASS_NUMBER_REGEX, classNumber)) {
            throw new InvalidInputException(ErrorMessage.INVALID_CLASS_NUMBER);
        }
    }

    public String getClassNumber() {
        return classNumber;
    }
}