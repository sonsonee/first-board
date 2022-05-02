package myfirst.board.repository;

import myfirst.board.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PostRepository {

    @Autowired
    EntityManager em;

    @Transactional
    public void save(Post post) {
        em.persist(post);
    }

    @Transactional(readOnly = true)
    public Post findOne(Long postId) {
        return em.find(Post.class, postId);
    }

    @Transactional(readOnly = true)
    public List<Post> findByTitle(String title) {

        return null;
    }
}
