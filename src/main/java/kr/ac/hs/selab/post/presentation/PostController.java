package kr.ac.hs.selab.post.presentation;

import kr.ac.hs.selab.auth.dto.AuthPrincipal;
import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.domain.vo.Title;
import kr.ac.hs.selab.board.dto.BoardDto;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.post.dto.PostDto;
import kr.ac.hs.selab.post.dto.PostMakeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/posts")
    public String post(@RequestParam("title") Title title, Model model) {
        List<BoardDto> boards = boardService.findAll();
        model.addAttribute("boards", boards);

        List<PostDto> postDto = boardService.findPosts(title);
        model.addAttribute("posts", postDto);
        return "fragments/post/posts";
    }

    @GetMapping("/post")
    public String makePost(Model model) {
        List<BoardDto> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        model.addAttribute("post", new PostMakeDto());
        return "fragments/post/create-post";
    }

    @PostMapping("/post")
    public String makePost(@AuthenticationPrincipal AuthPrincipal principal, Model model, PostMakeDto postDto, RedirectAttributes redirectAttributes) {
        List<BoardDto> boards = boardService.findAll();
        model.addAttribute("boards", boards);

        Email email = new Email(principal.getUsername());

        Title title = new Title("자유 게시판");
        Member member = memberService.findByEmail(email);

        boardService.createPost(member, postDto, title);
        redirectAttributes.addAttribute("title", title.getTitle());
        return "redirect:/posts";
    }
}
