package kr.ac.hs.selab.post.presentation;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.domain.vo.Title;
import kr.ac.hs.selab.board.dto.BoardDto;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final BoardService boardService;

    @GetMapping("/post")
    public String post(@RequestParam("title") Title title, Model model) {
        List<BoardDto> boards = boardService.boards();
        model.addAttribute("boards", boards);

        List<PostDto> posts = boardService.board(title).getPosts().stream().map(post -> new PostDto(post.getSubTitle())).collect(Collectors.toList());


        model.addAttribute("posts", posts);
        return "/fragments/post/post";
    }

}
