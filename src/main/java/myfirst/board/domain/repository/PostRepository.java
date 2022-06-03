package myfirst.board.domain.repository;

import myfirst.board.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    public boolean existsById(Long id);
}
