package kr.ac.hs.selab.member.presentation;

import kr.ac.hs.selab.auth.dto.CustomUser;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.dto.MemberSignUpDto;
import kr.ac.hs.selab.member.dto.MemberSocialSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
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
    public String sign() {
        return "/fragments/member/sign";
    }

    @PostMapping("sign")
    public String sign(@Valid MemberSignUpDto memberSignUpDto) {
        memberService.createMember(memberSignUpDto);
        return "redirect:/auth/login";
    }

    @GetMapping("signup/social")
    public String signUpSocial() {
        return "fragments/member/social-sign";
    }

    @PostMapping("signup/social")
    public String signUpSocial(@AuthenticationPrincipal CustomUser customUser,
                               @Valid MemberSocialSignUpDto memberSocialSignUpDto) {
        memberService.updateSocialMember(customUser.getId(), memberSocialSignUpDto);
        return "fragments/index";
    }

    @GetMapping("login")
    public String login() {
        return "/fragments/member/login";
    }
}
