package kr.ac.hs.selab.post.application;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.NonExitsException;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.converter.PostConverter;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostDetailDto;
import kr.ac.hs.selab.post.dto.PostDto;
import kr.ac.hs.selab.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostConverter postConverter;
    private final BoardService boardService;
    private final MemberService memberService;
    private final PostRepository postRepository;

    @Transactional
    public Long create(Long id, String email, PostDto dto) {
        Member member = memberService.findByEmail(email)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.IS_NOT_EXITS_MEMBER));

        Board board = boardService.findById(id);

        Post post = postConverter.toPost(dto, member, board);
        member.addPost(post);
        board.addPost(post);
        return post.getId();
    }

    @Transactional(readOnly = true)
    public Page<PostDetailDto> findAll(Long id, Pageable pageable) {
        return postRepository.findAllByPostBoard(id, pageable)
                .map(postConverter::toPostDetailDto);
    }

    @Transactional(readOnly = true)
    public PostDetailDto findByBoardIdWithPostId(Long boardId, Long postId) {
        Post post = postRepository.findByIdAndPostBoard(postId, boardId)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.IS_NOT_EXITS_POST));
        return postConverter.toPostDetailDto(post);
    }

    @Transactional
    public void delete(Long boardId, Long postId) {
        postRepository.deleteByIdAndPostBoard(boardId, postId);
    }

    @Transactional
    public void update(Long boardId, Long postId, PostDto dto) {
        Post post = postRepository.findByIdAndPostBoard(postId, boardId)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.IS_NOT_EXITS_POST));
        post.update(dto.getTitle(), dto.getContent());
    }

}
