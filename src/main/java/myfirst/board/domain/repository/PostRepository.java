package myfirst.board.domain.repository;

import myfirst.board.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {


/*

    private final EntityManager em;

    @Transactional
    public Long save(Post post) {
        em.persist(post);
        return post.getId();
    }

    @Transactional(readOnly = true)
    public Post findById(Long postId) {
        return em.find(Post.class, postId);
    }

    @Transactional(readOnly = true)
    public List<Post> findByTitle(String title) {
        return em.createQuery("select p from Post p where p.title like :title", Post.class)
                .setParameter("title", title)
                .getResultList();
    }

//    public List<Post> findByWriter() {}

    @Transactional
    public void delete(Long id) {
        Post post = findById(id);
        em.remove(post);
    }
*/
}
