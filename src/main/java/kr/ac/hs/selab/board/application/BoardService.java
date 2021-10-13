package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardDetailDto;
import kr.ac.hs.selab.board.dto.BoardDto;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import kr.ac.hs.selab.exception.DuplicationException;
import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.NonExistBoard;
import kr.ac.hs.selab.exception.OverabundanceException;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostDto;
import kr.ac.hs.selab.post.dto.PostMakeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final static int MAX_BOARD_COUNT = 10;
    private final BoardConverter boardConverter;
    private final BoardRepository boardRepository;

    // 전체 조회 <-- 상단 헤더에 게시판 보이기 위해서
    @Transactional(readOnly = true)
    public List<BoardDto> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(boardConverter::toBoardDto)
                .collect(Collectors.toList());
    }

    // 게시판 생성하기
    @Transactional
    public void createBoard(BoardDto dto) {
        if (isCorrectRange()) {
            throw new OverabundanceException(ErrorMessage.OVERABUNDANCE_BOARD_COUNT);
        }
        if (existsByTitle(dto.getTitle())) {
            throw new DuplicationException(ErrorMessage.DUPLICATION_BOARD_TITLE);
        }
        Board board = boardConverter.toBoard(dto);
        boardRepository.save(board);
    }

    // 게시판 생성 범위
    private boolean isCorrectRange() {
        return MAX_BOARD_COUNT < boardRepository.count();
    }

    // 게시판을 생성하는데, 이미 있는 제목이면 제한 걸기
    private boolean existsByTitle(String title) {
        return boardRepository.existsByTitle(title);
    }

    // 게시판 상세보기
    @Transactional(readOnly = true)
    public BoardDetailDto inquire(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NonExistBoard(ErrorMessage.IS_NOT_EXIT_BOARD));
        return boardConverter.toBoardDetailDto(board);
    }

    @Transactional
    public void update(Long id, BoardDto dto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NonExistBoard(ErrorMessage.IS_NOT_EXIT_BOARD));
        board.update(dto.getTitle(), dto.getContent());
    }

    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<PostDto> findPosts(Title title) {
        List<Post> posts = boardRepository.findBoardByTitle(title)
                .orElseThrow(() -> new NonExistBoard(ErrorMessage.IS_NOT_EXIT_BOARD)).getPosts();
        return makePostDto(posts);
    }

    private List<PostDto> makePostDto(List<Post> posts) {
        return posts.stream()
                .map(post -> new PostDto(post.getSubTitle()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createPost(Member member, PostMakeDto dto, Title title) {
        // Optional map 긔긔!~
        Board board = boardRepository.findBoardByTitle(title)
                .orElseThrow(() -> new NonExistBoard(ErrorMessage.IS_NOT_EXIT_BOARD));
        board.post(dto, member);
    }
}
