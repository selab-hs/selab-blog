package kr.ac.hs.selab.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class BoardDetailDto {
    @NonNull
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String content;
}
