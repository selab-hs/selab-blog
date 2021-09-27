package kr.ac.hs.selab.board.presentation;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping({"/", ""})
    public String board(Model model) {
        model.addAttribute("createBoardDto", new BoardDto());
        return "/fragments/board/create-board";
    }

    @PostMapping({"/", ""})
    public String board(BoardDto dto) {
        boardService.createBoard(dto);
        return "redirect:/";
    }
}
