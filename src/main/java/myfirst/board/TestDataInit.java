package myfirst.board;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.entity.Comment;
import myfirst.board.domain.entity.Member;
import myfirst.board.domain.entity.Post;
import myfirst.board.domain.repository.CommentRepository;
import myfirst.board.domain.repository.MemberRepository;
import myfirst.board.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    /**
     * 테스트용 데이터 추가
     */
//    @PostConstruct
    public void init() {

/*        Member member1 = Member.builder().loginId("test1").password("test!").nickname("테스트1").email("test1@test.com").build();
        memberRepository.save(member1);

        Member member2 = Member.builder().loginId("test2").password("test!").nickname("테스트2").email("test2@test.com").build();
        memberRepository.save(member2);

        Post post1 = Post.builder().title("test:Lorem Ipsum").content("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed placerat odio lorem, pretium efficitur erat laoreet non. Curabitur tempor nunc facilisis eros elementum vulputate vel non turpis. Maecenas justo arcu, euismod nec nisl eget, malesuada ultrices velit. Morbi tincidunt tempor hendrerit. Nam consequat facilisis urna, eu fringilla magna laoreet sit amet. Nullam ac aliquam massa. Aliquam fringilla, magna imperdiet rutrum pharetra, eros urna porta magna, a laoreet eros nibh quis nibh. Sed volutpat massa vitae neque ultrices gravida non eu orci. Praesent porta mattis vulputate. Mauris eget leo vitae erat venenatis laoreet. Aenean dignissim leo in augue pellentesque ornare vel vitae velit.").views(0L).member(member1).build();
        postRepository.save(post1);

        Post post2 = Post.builder().title("테스트 제목").content("테스트입니다.").views(0L).member(member2).build();
        postRepository.save(post2);

        Comment comment1_1 = Comment.builder().member(member1).post(post1).content("테스트용 댓글입니다.").build();
        commentRepository.save(comment1_1);

        Comment comment1_2 = Comment.builder().member(member2).post(post1).content("테스트용 댓글입니다.222222222").build();
        commentRepository.save(comment1_2);

        Comment comment1_3 = Comment.builder().member(member1).post(post1).content("테스트용 댓글입니다.3 \n하하").build();
        commentRepository.save(comment1_3);*/

 /*       Optional<Member> member = memberRepository.findById(1L);

        for (int i = 1; i < 101; i++) {
            Post post = Post.builder().title("test:Lorem Ipsum" + i).content("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed placerat odio lorem, pretium efficitur erat laoreet non. Curabitur tempor nunc facilisis eros elementum vulputate vel non turpis. Maecenas justo arcu, euismod nec nisl eget, malesuada ultrices velit. Morbi tincidunt tempor hendrerit. Nam consequat facilisis urna, eu fringilla magna laoreet sit amet. Nullam ac aliquam massa. Aliquam fringilla, magna imperdiet rutrum pharetra, eros urna porta magna, a laoreet eros nibh quis nibh. Sed volutpat massa vitae neque ultrices gravida non eu orci. Praesent porta mattis vulputate. Mauris eget leo vitae erat venenatis laoreet. Aenean dignissim leo in augue pellentesque ornare vel vitae velit.").views(0L).member(member.get()).build();
            postRepository.save(post);
        }*/
    }

}