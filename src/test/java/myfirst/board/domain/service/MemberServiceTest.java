package myfirst.board.domain.service;

import myfirst.board.domain.dto.MemberDto;
import org.junit.jupiter.api.Assertions;
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

    @Autowired
    MemberService memberService;

    @Test
    void join() {
        //given
        MemberDto.Request memberDto = new MemberDto.Request("loginId1", "password1",
                "nickname1", "email@mail.com");

        //when
        Long joinMemberId = memberService.join(memberDto);

        //then
        MemberDto.Response foundMember = memberService.findById(joinMemberId);
        assertThat(foundMember.getNickname()).isEqualTo(memberDto.getNickname());
    }

    @Test
    void 중복_회원_조회() {
        //given
        MemberDto.Request memberDto = new MemberDto.Request("loginId1", "password1",
                "nickname1", "email@mail.com");
        Long joinMemberId = memberService.join(memberDto);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.validateDuplicateLoginId(memberDto.getLoginId()));

        //then
        System.out.println("e = " + e.getMessage());
        assertEquals("이미 존재하는 아이디입니다.", e.getMessage());

    }

    @Test
    public void login() {
        //given
        MemberDto.Request memberDto = new MemberDto.Request("loginId1", "password1",
                "nickname1", "email@mail.com");
        Long joinMemberId = memberService.join(memberDto);

        //when
        Long result = memberService.login("loginId1", "password1");
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.login("loginId2", "www"));

        //then
        assertThat(result).isEqualTo(joinMemberId);
        assertEquals("아이디와 비밀번호가 맞지 않습니다.", e.getMessage());
        System.out.println("result = " + result);

    }

    @Test
    public void 미등록_회원_조회() {
        //given
        MemberDto.Request memberDto = new MemberDto.Request("loginId1", "password1",
                "nickname1", "email@mail.com");
        Long joinMemberId = memberService.join(memberDto);

        //when
        IllegalStateException e1 = assertThrows(IllegalStateException.class, () -> memberService.findById(joinMemberId));
        IllegalStateException e2 = assertThrows(IllegalStateException.class, () -> memberService.findByLoginId("lol"));

        //then
        System.out.println("e1 = " + e1.getMessage());
        System.out.println("e2 = " + e2.getMessage());
        assertEquals("#10의 회원이 없습니다.", e1.getMessage());
        assertEquals("아이디 lol의 회원이 없습니다.", e2.getMessage());

    }
}