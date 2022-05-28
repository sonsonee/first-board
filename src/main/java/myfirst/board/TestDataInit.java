package myfirst.board;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.entity.Comment;
import myfirst.board.domain.entity.Member;
import myfirst.board.domain.entity.Post;
import myfirst.board.domain.repository.MemberRepository;
import myfirst.board.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        Member member1 = Member.builder().loginId("test1").password("test!").nickname("테스트1").email("test1@test.com").build();
        memberRepository.save(member1);

        Member member2 = Member.builder().loginId("test2").password("test!").nickname("테스트2").email("test2@test.com").build();
        memberRepository.save(member2);

        Post post1 = Post.builder().title("title:test").content("test content").views(0L).member(member1).build();
        postRepository.save(post1);

        Post post2 = Post.builder().title("테스트 제목").content("테스트입니다.").views(0L).member(member2).build();
        postRepository.save(post2);
    }

}