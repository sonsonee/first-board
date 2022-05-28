package myfirst.board.domain.repository;

import myfirst.board.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    public void save() {
        //given
        Member member = Member.builder().loginId("login1").password("password1").nickname("테스트").email("test@test.com").build();
        System.out.println(member.getId());

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(savedMember.getId()).isEqualTo(member.getId());
        assertThat(savedMember).isEqualTo(member);
    }

    @Test
    void findByLoginId() {
        //given
        Member member = Member.builder().loginId("login1").password("password1").nickname("테스트").email("test@test.com").build();
        memberRepository.save(member);

        //when
        Member findMember = memberRepository.findByLoginId(member.getLoginId()).get();

        //then
        assertThat(findMember.getLoginId()).isEqualTo(member.getLoginId());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());

    }

}