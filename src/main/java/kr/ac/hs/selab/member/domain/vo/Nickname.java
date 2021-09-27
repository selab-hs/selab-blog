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
public class Nickname {

    @Transient
    private static final String NICKNAME_REGEX = "^[a-zA-Z가-힣0-9]{2,20}$";

    @Column(name = "member_nickname")
    private String nickname;

    public Nickname(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }

    public void validate(String nickname) {
        if (!Pattern.matches(NICKNAME_REGEX, nickname)) {
            throw new InvalidInputException(ErrorMessage.INVALID_NICKNAME);
        }
    }

    public String getNickname() {
        return nickname;
    }

    protected Nickname() {

    }
}
