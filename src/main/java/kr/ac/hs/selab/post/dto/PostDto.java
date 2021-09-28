package kr.ac.hs.selab.post.dto;

import kr.ac.hs.selab.post.domain.vo.SubTitle;
import lombok.Data;
import lombok.NonNull;

@Data
public class PostDto {
    @NonNull
    private SubTitle subTitle;

    public PostDto() {

    }

    public PostDto(@NonNull SubTitle subTitle) {
        this.subTitle = subTitle;
    }
}
