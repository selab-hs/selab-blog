package kr.ac.hs.selab.post.application;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.NonExitsException;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import kr.ac.hs.selab.post.converter.PostConverter;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostDetailDto;
import kr.ac.hs.selab.post.dto.PostDto;
import kr.ac.hs.selab.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostConverter postConverter;
    private final BoardService boardService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long create(Long id, Long memberId, PostDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.NON_EXISTENT_USER));

        Board board = boardService.findById(id);

        Post post = postConverter.toPost(dto, member, board);
        member.addPost(post);
        board.addPost(post);
        return post.getId();
    }

    @Transactional(readOnly = true)
    public Page<PostDetailDto> findAll(String boardTitle, Pageable pageable) {
        Board board = boardRepository.findBoardByTitle(boardTitle)
                .orElseThrow(() -> new RuntimeException("exception"));
        return postRepository.findAllByBoard(board, pageable)
                .map(postConverter::toPostDetailDto);
    }

    @Transactional(readOnly = true)
    public PostDetailDto findByBoardIdWithPostId(Long boardId, Long postId) {
        Post post = postRepository.findByIdAndBoard(postId, boardId)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.IS_NOT_EXITS_POST));
        return postConverter.toPostDetailDto(post);
    }

    @Transactional
    public void delete(Long boardId, Long postId) {
        postRepository.deleteByIdAndBoard(boardId, postId);
    }

    @Transactional
    public void update(Long boardId, Long postId, PostDto dto) {
        Post post = postRepository.findByIdAndBoard(postId, boardId)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.IS_NOT_EXITS_POST));
        post.update(dto.getTitle(), dto.getContent());
    }

}
