package kr.ac.hs.selab.board.converter;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardDetailDto;
import kr.ac.hs.selab.board.dto.BoardDto;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter {
    public Board toBoard(BoardDto dto) {
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public BoardDto toBoardDto(Board board) {
        return BoardDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    public BoardDetailDto toBoardDetailDto(Board board) {
        return BoardDetailDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
