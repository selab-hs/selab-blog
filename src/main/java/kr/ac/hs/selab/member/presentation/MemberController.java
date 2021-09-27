package kr.ac.hs.selab.member.presentation;

import kr.ac.hs.selab.auth.dto.AuthPrincipal;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.dto.MemberSignDto;
import kr.ac.hs.selab.member.dto.SocialMemberSignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("signup/social")
    public String signUpSocial(@AuthenticationPrincipal AuthPrincipal authPrincipal) {
        validateSocialSingUp(authPrincipal);

        return "fragments/member/social-sign-up";
    }

    @PostMapping("signup/social")
    public String signUpSocial(@AuthenticationPrincipal AuthPrincipal authPrincipal,
                               @Valid SocialMemberSignDto socialMemberSign) {
        validateSocialSingUp(authPrincipal);

        memberService.updateSocialMemberInfo(authPrincipal.getId(), socialMemberSign);
        return "fragments/index";
    }

    private void validateSocialSingUp(AuthPrincipal authPrincipal) {
        if (!authPrincipal.isSocial()) {
            throw new RuntimeException("소셜 계정이 아닙니다.");
        }
    }

    @GetMapping("login")
    public String login() {
        return "/fragments/member/login";
    }
}
