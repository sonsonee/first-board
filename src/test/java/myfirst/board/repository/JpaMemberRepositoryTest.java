package myfirst.board.repository;

import myfirst.board.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class JpaMemberRepositoryTest {

    @Autowired
    JpaMemberRepository repository;

    @Test
    void save() {
        //given
        Member member = new Member();
        member.initMemberInfo("member1", "password11", "firstMember", "hfdske3@gmail.com", LocalDateTime.now());
        repository.save(member);

        //when
        Member findMember = repository.findById(member.getId()).get();

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

/*    @Test
    void findById() {
    }

    @Test
    void findByNickname() {
    }

    @Test
    void findByLoginId() {
    }

    @Test
    void findAll() {
    }*/
}