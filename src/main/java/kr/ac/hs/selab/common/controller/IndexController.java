package kr.ac.hs.selab.common.controller;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final BoardService boardService;

    @GetMapping({"", "/", "index"})
    public String index(Model model) {
        List<BoardDto> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "fragments/index";
    }
}
