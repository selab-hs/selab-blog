package kr.ac.hs.selab.post.infrastructure;

import kr.ac.hs.selab.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByPostBoard(Long id, Pageable pageable);

    Optional<Post> findByIdAndPostBoard(Long postId, Long boardId);

    void deleteByIdAndPostBoard(Long postId, Long boardId);
}
