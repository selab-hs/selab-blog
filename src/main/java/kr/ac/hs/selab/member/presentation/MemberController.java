package kr.ac.hs.selab.member.presentation;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.dto.MemberSignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("auth")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("sign")
    public String sign(Model model) {
        model.addAttribute("memberSignDto", new MemberSignDto());
        return "/fragments/member/sign";
    }

    @PostMapping("sign")
    public String sign(@Valid MemberSignDto request) {
        memberService.createMember(request);
        return "redirect:/auth/login";
    }

    @GetMapping("login")
    public String login() {
        return "/fragments/member/login";
    }
}
