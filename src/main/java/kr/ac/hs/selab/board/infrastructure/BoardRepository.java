package kr.ac.hs.selab.board.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.domain.vo.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAll();

    Optional<Board> findByTitle(Title title);
}
