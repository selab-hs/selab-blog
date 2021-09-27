package kr.ac.hs.selab.board.presentation;

import kr.ac.hs.selab.auth.dto.AuthPrincipal;
import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.dto.BoardDto;
import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.IncorrectPermissionException;
import kr.ac.hs.selab.member.domain.vo.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String board(@AuthenticationPrincipal AuthPrincipal principal, BoardDto dto) {
        if (principal.getRole() != Role.ADMIN) {
            throw new IncorrectPermissionException(ErrorMessage.NO_PERMISSION_MEMBER);
        }
        boardService.createBoard(dto);
        return "redirect:/";
    }
}
