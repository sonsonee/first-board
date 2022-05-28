package myfirst.board.domain.repository;

import myfirst.board.domain.entity.Member;
import myfirst.board.domain.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired PostRepository postRepository;

    @Test
    void save() {
        //given
        Member member = Member.builder().loginId("test").password("test!").nickname("테스트").email("test@test.com").build();
        Post post = Post.builder().title("Post for Test").content("It's Test.").views(0L).member(member).build();

        //when
        Post savedPost = postRepository.save(post);

        //then
        Assertions.assertThat(post.getId()).isEqualTo(savedPost.getId());
        Assertions.assertThat(post).isEqualTo(savedPost);

    }

}