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
public class PhoneNumber {
    // :: Mobile Phone Identification Number :: 010
    @Transient
    private static final String PHONE_NUMBER_REGEX = "^010[0-9]{8}$";

    @Column(name = "member_phone_number")
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void validate(String phoneNumber) {
        if (!Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber)) {
            throw new InvalidInputException(ErrorMessage.INVALID_PHONE_NUMBER);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    protected PhoneNumber() {
        
    }
}