package kr.ac.hs.selab.board.dto;

import kr.ac.hs.selab.board.domain.vo.Content;
import kr.ac.hs.selab.board.domain.vo.Title;
import lombok.Data;
import lombok.NonNull;

@Data
public class BoardDto {
    @NonNull
    private Title title;

    @NonNull
    private Content content;

    public BoardDto() {
    }
}
