package myfirst.board.repository;

import myfirst.board.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByLoginId(String loginId);

/*    @Autowired
    EntityManager em;

    @Transactional
    public void save(Member member) {
        em.persist(member);
    }

    @Transactional(readOnly = true)
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    @Transactional(readOnly = true)
    public List<Member> findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }*/
}
