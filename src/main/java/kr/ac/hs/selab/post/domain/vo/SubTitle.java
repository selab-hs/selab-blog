package kr.ac.hs.selab.post.domain.vo;

import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.InvalidInputException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class SubTitle {
    @Transient
    private static final String SUB_TITLE_REGEX = "^.{5,30}$";

    @Column(name = "post_sub_title")
    private String subTitle;

    public SubTitle(String subTitle) {
        validate(subTitle);
        this.subTitle = subTitle;
    }

    public void validate(String subTitle) {
        if (!Pattern.matches(SUB_TITLE_REGEX, subTitle)) {
            throw new InvalidInputException(ErrorMessage.INVALID_SUB_TITLE);
        }
    }

    protected SubTitle() {
    }

    public String getSubTitle() {
        return subTitle;
    }
}
