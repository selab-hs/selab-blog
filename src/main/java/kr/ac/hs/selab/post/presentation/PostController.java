package kr.ac.hs.selab.post.presentation;

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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/board/{boardTitle}/post/insert")
    public String insert(@PathVariable String boardTitle, Model model) {
        model.addAttribute("boardTitle", boardTitle);
        model.addAttribute("post", new PostDto());
        return "fragments/post/create-post";
    }

    @PostMapping(value = "/board/{boardTitle}/post/insert")
    public String insert(@PathVariable String boardTitle, @AuthenticationPrincipal AuthUser authUser, PostDto postDto) throws UnsupportedEncodingException {

        postService.create(boardTitle, authUser.getId(), postDto);

        String path = URLEncoder.encode(boardTitle, "UTF-8");
        return "redirect:/board/" + path + "/post";
    }

    @GetMapping("/board/{boardTitle}/post")
    public String inquire(@PathVariable String boardTitle, Pageable pageable, Model model) {
        Page<PostDetailDto> posts = postService.findAll(boardTitle, pageable);
        model.addAttribute("boardTitle", boardTitle);
        model.addAttribute("posts", posts);
        return "fragments/post/posts";
    }

    @GetMapping("/board/{boardTitle}/post/{postId}")
    public String inquire(@PathVariable String boardTitle, @PathVariable Long postId, Model model) {
        PostDetailDto post = postService.find(boardTitle, postId);
        model.addAttribute("post", post);
        return "fragments/post/post-detail";
    }

    @DeleteMapping("/board/{boardId}/post/{postId}")
    public String delete(@PathVariable Long boardId, @PathVariable Long postId ) {
        postService.delete(boardId, postId);
        return "redirect:/board/" + boardId + "/post";
    }

    @PatchMapping("/board/{boardTitle}/post/{postId}")
    public String edit(@PathVariable String boardTitle, @PathVariable Long postId,  PostDto dto) {
        postService.update(boardTitle, postId, dto);
        return "redirect:/board/" + boardTitle + "/post/" + postId;
    }
}