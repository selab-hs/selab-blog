package kr.ac.hs.selab.post.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByBoard(Board board, Pageable pageable);

    Optional<Post> findByIdAndBoard(Long postId, Long boardId);

    void deleteByIdAndBoard(Long postId, Long boardId);
}
