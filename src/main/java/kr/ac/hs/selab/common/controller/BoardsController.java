package kr.ac.hs.selab.common.controller;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class BoardsController {
    private final BoardService boardService;

    @ModelAttribute
    public void board(Model model) {
        List<BoardDto> boards = boardService.findAll();
        model.addAttribute("boards", boards);
    }
}
