package kr.ac.hs.selab.board.presentation;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/insert")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String insert(Model model) {
        model.addAttribute("createBoardDto", new BoardDto());
        return "/fragments/board/create-board";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String insert(@Valid BoardDto dto) {
        boardService.createBoard(dto);
        return "redirect:/board/insert";
    }

    @GetMapping("/inquire")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String inquire() {
        return "/fragments/board/boards";
    }

    @GetMapping("/inquire/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("boardDetail", boardService.inquire(id));
        return "/fragments/board/board-detail";
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String edit(@PathVariable Long id, BoardDto dto) {
        boardService.update(id, dto);
        return "redirect:/inquire/" + id;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/inquire";
    }
}