package kr.ac.hs.selab.member.presentation;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.dto.BoardDto;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.dro.MemberSignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("auth")
public class MemberController {
    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping("sign")
    public String sign(Model model) {
        model.addAttribute("memberSignDto", new MemberSignDto());
        List<BoardDto> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return "/fragments/member/sign";
    }

    @PostMapping("sign")
    public String sign(@Valid MemberSignDto request) {
        memberService.createMember(request);
        return "redirect:/auth/login";
    }

    @GetMapping("login")
    public String login(Model model) {
        List<BoardDto> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return "/fragments/member/login";
    }
}
