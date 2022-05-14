package myfirst.board.domain.service;

import myfirst.board.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired LoginService loginService;
    @Autowired MemberService memberService;

    @Test
    public void login() {
        //given
        Member member = new Member("test1", "pw12345", "test", "test@naver.com");
        memberService.join(member);

        //when
        Member result1 = loginService.login("test1", "pw12345");
        Member result2 = loginService.login("test1", "www");

        //then
        assertThat(result1).isEqualTo(member);
        assertThat(result2).isEqualTo(null);
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        System.out.println("member = " + member);
    }

}