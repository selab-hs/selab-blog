package kr.ac.hs.selab.member.presentation;

import kr.ac.hs.selab.common.dto.AuthUser;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.dto.MemberBasicSignupRequest;
import kr.ac.hs.selab.member.dto.MemberPrivacyRequest;
import kr.ac.hs.selab.member.dto.MemberSocialSignupRequest;
import kr.ac.hs.selab.oauth.dto.CustomOAuth2User;
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

    @GetMapping("login")
    public String login() {
        return "/fragments/member/login";
    }

    @GetMapping("signup/basic")
    public String signupBasic() {
        return "/fragments/member/signup-basic";
    }

    @PostMapping("signup/basic")
    public String signupBasic(@Valid MemberBasicSignupRequest request) {
        memberService.create(request);
        return "redirect:/auth/login";
    }

    @GetMapping("signup/social")
    public String signupSocial() {
        return "fragments/member/signup-social";
    }

    @PostMapping("signup/social")
    public String signupSocial(@AuthenticationPrincipal CustomOAuth2User authUser,
                               @Valid MemberSocialSignupRequest request) {
        memberService.updateSocialInfo(authUser.getId(), request);
        return "fragments/index";
    }

    @GetMapping("edit/privacy")
    public String editPrivacy() {
        return "fragments/member/edit-privacy";
    }

    @PostMapping("edit/privacy")
    public String editPrivacy(@AuthenticationPrincipal AuthUser authUser,
                              @Valid MemberPrivacyRequest request) {
        memberService.updatePrivacy(authUser.getId(), request);
        return "fragments/index";
    }
}