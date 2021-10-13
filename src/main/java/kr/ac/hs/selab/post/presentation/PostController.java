package kr.ac.hs.selab.post.presentation;

import kr.ac.hs.selab.auth.dto.AuthPrincipal;
import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.dto.BoardDto;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.dto.PostDetailDto;
import kr.ac.hs.selab.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final BoardService boardService;
    private final MemberService memberService;

    // 삭제
    // 수정
    // 전체조회 -> Pageable하게
    // 단건조회

    // 삽입
    @GetMapping("/board/{id}/post/insert")
    public String insert(@PathVariable Long id, Model model) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        model.addAttribute("boardId", id);
        model.addAttribute("post", new PostDto());
        return "fragments/post/create-post";
    }

    // 삽입
    @PostMapping("/board/{id}/post/insert")
    public String insert(@AuthenticationPrincipal AuthPrincipal authPrincipal, Long id, Model model, PostDto postDto) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        Long postId = postService.create(id, authPrincipal.getUsername(), postDto);

        return "redirect:/board/" + id + "/post/" + postId;
    }

    // 전체 조회
    @GetMapping("/board/{id}/post")
    public String inquire(@PathVariable Long id, Pageable pageable, Model model) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        Page<PostDetailDto> all = postService.findAll(id, pageable);
        model.addAttribute("posts", all);
        return "fragments/post/posts";
    }

    @GetMapping("/board/{boardId}/post/{postId}")
    public String inquire(@PathVariable Long boardId, @PathVariable Long postId, Model model) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        PostDetailDto post = postService.findByBoardIdWithPostId(boardId, postId);
        model.addAttribute("post", post);
        return "fragments/post/post-detail";
    }

    @DeleteMapping("/board/{boardId}/post/{postId}")
    public String delete(@PathVariable Long boardId, @PathVariable Long postId, Model model) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        postService.delete(boardId, postId);
        return "redirect:/board/" + boardId + "/post";
    }

    @GetMapping("/board/{boardId}/post/{postId}")
    public String edit(@PathVariable Long boardId, @PathVariable Long postId, Model model) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        return "fragments/post/post-update";
    }

    @PatchMapping("/board/{boardId}/post/{postId}")
    public String edit(@PathVariable Long boardId, @PathVariable Long postId, Model model, PostDto dto) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        postService.update(boardId, postId, dto);
        return "redirect:/board/" + boardId + "/post/" + postId;
    }
}
