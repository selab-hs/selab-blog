package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.domain.vo.Title;
import kr.ac.hs.selab.board.dto.BoardDto;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import kr.ac.hs.selab.exception.NonExistBoard;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostDto;
import kr.ac.hs.selab.post.dto.PostMakeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static kr.ac.hs.selab.exception.ErrorMessage.IS_NOT_EXIT_BOARD;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void createBoard(BoardDto dto) {
        Board newBoard = Board.of(dto);
        boardRepository.save(newBoard);
    }

    @Transactional(readOnly = true)
    public List<BoardDto> findBoards() {
        return boardRepository.findAll()
                .stream()
                .map(
                        board -> new BoardDto(
                                board.getTitle(),
                                board.getContent()
                        )
                ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostDto> findPosts(Title title) {
        List<Post> posts = boardRepository.findBoardByTitle(title)
                .orElseThrow(() -> new NonExistBoard(IS_NOT_EXIT_BOARD)).getPosts();
        return makePostDto(posts);
    }

    private List<PostDto> makePostDto(List<Post> posts) {
        return posts.stream()
                .map(post -> new PostDto(post.getSubTitle()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createPost(Member member, PostMakeDto dto, Title title) {
        Board board = boardRepository.findBoardByTitle(title).orElseThrow(() -> new NonExistBoard(IS_NOT_EXIT_BOARD));
        board.post(dto, member);
    }

}
