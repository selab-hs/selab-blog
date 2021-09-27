package kr.ac.hs.selab.board.domain.vo;

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
public class Title {
    @Transient
    private static final String TITLE_REGEX = "^.{5,10}$";

    @Column(name = "board_title")
    private String title;

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    public void validate(String title) {
        if (!Pattern.matches(TITLE_REGEX, title)) {
            throw new InvalidInputException(ErrorMessage.INVALID_TITLE);
        }
    }

    protected Title() {
    }

    public String getTitle() {
        return title;
    }
}
