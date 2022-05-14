package myfirst.board;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.Member;
import myfirst.board.domain.repository.MemberRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        Member member = new Member("test", "test!", "테스트", "test@gmail.com");
        memberRepository.save(member);
    }

}