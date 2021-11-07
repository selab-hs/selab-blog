package kr.ac.hs.selab.post.presentation;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.common.dto.AuthUser;
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

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final BoardService boardService;

    // 삽입
    @GetMapping("/board/{boardTitle}/post/insert")
    public String insert(@PathVariable String boardTitle, Model model) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        model.addAttribute("boardTitle", boardTitle);
        model.addAttribute("post", new PostDto());
        return "fragments/post/create-post";
    }

    // 삽입
    @PostMapping("/board/{boardTitle}/post/insert")
    public String insert(@PathVariable String boardTitle, @AuthenticationPrincipal AuthUser authUser, Model model, PostDto postDto) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        Long postId = postService.create(boardTitle, authUser.getId(), postDto);

        return "redirect:/board/" + boardTitle + "/post/insert";
    }

    // 전체 조회
    @GetMapping("/board/{boardTitle}/post")
    public String inquire(@PathVariable String boardTitle, Pageable pageable, Model model) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        Page<PostDetailDto> posts = postService.findAll(boardTitle, pageable);
        model.addAttribute("boardTitle", boardTitle);
        model.addAttribute("posts", posts);
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

    @PatchMapping("/board/{boardId}/post/{postId}")
    public String edit(@PathVariable Long boardId, @PathVariable Long postId, Model model, PostDto dto) {
        // Board 출력 //
        model.addAttribute("boards", boardService.findAll());
        // Board 출력 //

        postService.update(boardId, postId, dto);
        return "redirect:/board/" + boardId + "/post/" + postId;
    }
}
