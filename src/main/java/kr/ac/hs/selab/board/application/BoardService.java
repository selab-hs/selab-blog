package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardDto;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void createBoard(BoardDto dto) {
        Board newBoard = Board.of(dto);
        boardRepository.save(newBoard);
    }
}
