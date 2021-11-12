package kr.ac.hs.selab.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class BoardDetailDto {
    private Long id;
    private String title;
    private String content;
}
