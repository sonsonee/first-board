package myfirst.board.domain.service;

import myfirst.board.domain.Member;
import myfirst.board.domain.repository.MemberRepository;
import myfirst.board.domain.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest // 스프링 부트 띄운 상태로 테스트
@Transactional // test 끝난 이후 rollback
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void join() {
        //given
        Member member = new Member("test1", "pw12345", "test", "test@naver.com");

        //when
        Long savedId = memberService.join(member);

        //then
        assertThat(savedId).isEqualTo(1L);
    }

    @Test
    void findByMemberId() {
        //given

        //when

        //then

    }
}