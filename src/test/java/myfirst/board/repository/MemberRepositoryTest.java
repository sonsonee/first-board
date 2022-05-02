package myfirst.board.repository;

import myfirst.board.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.initMemberInfo("test1", "pw12345", "member1", "test2343@naver.com", LocalDateTime.now());

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(1L);
        Assertions.assertThat(member).isEqualTo(findMember);

    }

}