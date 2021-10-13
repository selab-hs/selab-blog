package kr.ac.hs.selab.board.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAll();

    @Override
    long count();

    boolean existsByTitle(String title);

    @Override
    Optional<Board> findById(Long id);

    @Override
    void deleteById(Long id);

    Optional<Board> findBoardByTitle(Title title);
}
