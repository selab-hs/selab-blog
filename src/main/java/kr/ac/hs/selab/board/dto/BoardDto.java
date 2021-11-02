package kr.ac.hs.selab.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
public class BoardDto {
    @Pattern(regexp = "^.{3,10}$", message = "제목 형식이 옳바르지 않습니다.")
    private String title;

    @NotBlank(message = "게시판 소개 형식이 옳바르지 않습니다.")
    private String content;

    public BoardDto() {
    }
}
