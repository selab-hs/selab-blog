package kr.ac.hs.selab.post.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class PostDto {
    @Pattern(regexp = "^.{3,25}$", message = "제목 형식이 옳바르지 않습니다.")
    private String title;

    @NotBlank(message = "게시글 형식이 옳바르지 않습니다.")
    private String content;

    public PostDto() {
    }
}
