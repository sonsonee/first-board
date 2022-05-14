package myfirst.board.domain.repository;

import myfirst.board.domain.Member;
import myfirst.board.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    public void save() {
        //given
        Member member = new Member("test1", "pw12345", "member1", "test2343@naver.com");
        System.out.println(member.getId());

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(savedMember.getId()).isEqualTo(1L);
        assertThat(savedMember).isEqualTo(member);
    }

    @Test
    void findByLoginId() {
        //given
        Member member = new Member("test1", "pw12345", "test", "test@naver.com");
        memberRepository.save(member);

        //when
        Member findMember = memberRepository.findByLoginId(member.getLoginId()).get();

        //then
        assertThat(findMember.getLoginId()).isEqualTo(member.getLoginId());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());

    }

}