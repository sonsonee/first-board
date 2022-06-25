package myfirst.board.domain.repository;

import myfirst.board.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTitleContaining(String title, Pageable pageable);

    public boolean existsById(Long id);
}
