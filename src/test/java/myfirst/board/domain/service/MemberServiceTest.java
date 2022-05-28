package myfirst.board.domain.service;

import myfirst.board.domain.dto.MemberDto;
import myfirst.board.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest // 스프링 부트 띄운 상태로 테스트
@Transactional // test 끝난 이후 rollback
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void join() {
        //given
        MemberDto.Request memberDto = new MemberDto.Request("loginId1", "password1",
                "nickname1", "email@mail.com");

        //when
        MemberDto.Response joinMember = memberService.join(memberDto);

        //then
        assertThat(joinMember.getNickname()).isEqualTo(memberDto.getNickname());
    }

    @Test
    public void login() {
        //given
        MemberDto.Request memberDto = new MemberDto.Request("loginId1", "password1",
                "nickname1", "email@mail.com");
        memberService.join(memberDto);

        //when
        String result1 = memberService.login("loginId1", "password1");
        String result2 = memberService.login("test1", "www");

        //then
        assertThat(result1).isEqualTo(memberDto.getLoginId());
        assertThat(result2).isEqualTo(null);
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
    }

}