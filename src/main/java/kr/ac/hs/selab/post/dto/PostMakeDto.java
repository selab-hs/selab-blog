package kr.ac.hs.selab.post.dto;

import kr.ac.hs.selab.post.domain.vo.SubTitle;
import lombok.Data;
import lombok.NonNull;

@Data
public class PostMakeDto {
    @NonNull
    private SubTitle subTitle;

    @NonNull
    private String subContent;

    public PostMakeDto() {

    }

    public PostMakeDto(@NonNull SubTitle subTitle, @NonNull String subContent) {
        this.subTitle = subTitle;
        this.subContent = subContent;
    }
}
