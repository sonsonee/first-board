package myfirst.board.domain.repository;

import myfirst.board.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTitleContaining(String title, Pageable pageable);

    @Modifying
    @Query("UPDATE Post p SET p.views = :views WHERE p.id = :postId")
    int updateViews(@Param("postId") Long postId, @Param("views") Long views);

    public boolean existsById(Long id);
}
