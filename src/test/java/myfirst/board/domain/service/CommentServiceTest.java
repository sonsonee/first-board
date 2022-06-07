package myfirst.board.domain.service;

import myfirst.board.domain.dto.CommentDto;
import myfirst.board.domain.dto.MemberDto;
import myfirst.board.domain.dto.PostDto;
import myfirst.board.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired MemberService memberService;
    @Autowired PostService postService;
    @Autowired CommentService commentService;

    @Test
    void comment() {
        //given
        MemberDto.Request memberDto = MemberDto.Request.builder().loginId("loginId1").password("password!").nickname("nickname1").email("test1@test.com").build();
        Long joinMemberId = memberService.join(memberDto);

        PostDto.Request postDto = PostDto.Request.builder().title("테스트 제목").content("테스트입니다.").views(0L).build();
        Long postId = postService.post(postDto, joinMemberId);

        //when
        CommentDto.Request commentDto = CommentDto.Request.builder().content("안녕 난 댓글이야").build();
        commentService.comment(commentDto, joinMemberId, postId);

        //then

    }

}