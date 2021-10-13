package kr.ac.hs.selab.board.presentation;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.dto.BoardDetailDto;
import kr.ac.hs.selab.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    // 게시판 생성
    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String write(Model model) {
        // 상단 Board 제목 출력 //
        model.addAttribute("boards", boardService.findAll());
        // 상단 Board 제목 출력 //

        // dto 던지기
        model.addAttribute("createBoardDto", new BoardDto());
        return "/fragments/board/create-board";
    }

    // 게시판 생성
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String write(@Valid BoardDto dto, Model model) {
        // 상단 Board 제목 출력 //
        model.addAttribute("boards", boardService.findAll());
        // 상단 Board 제목 출력 //

        // create 진행하기
        boardService.createBoard(dto);
        return "redirect:/create";
    }

    // 게시판 전체 조회
    @GetMapping("/inquire")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String inquire(Pageable pageable, Model model) {
        // 상단 Board 제목 출력 //
        model.addAttribute("boards", boardService.findAll());
        // 상단 Board 제목 출력 //

        // Board 전체 출력
        model.addAttribute("boards", boardService.findAll());
        return "/fragments/board/boards";
    }

    // 게시판 상세 보기
    @GetMapping("/inquire/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String edit(@PathVariable Long id, Model model) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        model.addAttribute("boardDetail", boardService.inquire(id));
        return "/fragments/board/board-detail";
    }

    // 게시판 수정하기
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String edit(@PathVariable Long id, Model model, BoardDto dto) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        boardService.update(id, dto);
        return "redirect:/inquire/" + id;
    }

    // 게시판 삭제하기
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String delete(@PathVariable Long id, Model model) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        boardService.deleteById(id);
        return "redirect:/inquire";
    }
}