package myfirst.board.service;

import myfirst.board.domain.Member;
import myfirst.board.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 부트 띄운 상태로 테스트
@Transactional // test 끝난 이후 rollback
class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired EntityManager em;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.initMemberInfo("test1", "pw12345", "member1", "test2343@naver.com", LocalDateTime.now());

        //when
        Long savedId = memberService.join(member);

        em.flush();

        //then
        Member findMember = memberRepository.findById(savedId).get();
        assertThat(member).isEqualTo(findMember);

    }

    @Test
    void findByMemberId() {
        //given

        //when

        //then

    }
}