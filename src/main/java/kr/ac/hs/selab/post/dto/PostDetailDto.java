package kr.ac.hs.selab.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDetailDto {
    private Long id;
    private String title;
    private String content;
}
