package kr.ac.hs.selab.board.domain.vo;

import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.InvalidInputException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class Content {
    @Transient
    private static final String CONTENT_REGEX = "^.{5,50}$";

    @Column(name = "board_content")
    private String content;

    public Content(String content) {
        validate(content);
        this.content = content;
    }

    public void validate(String content) {
        if (!Pattern.matches(CONTENT_REGEX, content)) {
            throw new InvalidInputException(ErrorMessage.INVALID_CONTENT);
        }
    }

    public String getContent() {
        return content;
    }

    protected Content() {
    }
}
