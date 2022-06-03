package myfirst.board.domain.service;

import myfirst.board.domain.dto.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    /**
     * 댓글 작성
     */
    @Transactional
    public Long comment(CommentDto.Request commentDto, Long postId) {

        return null;

    }

    /**
     * 댓글 수정
     */
    @Transactional
    public void edit(Long commentId, String content) {

    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public void delete(Long commentId) {

    }

    /**
     * 댓글 목록 조회 - postDto 에 선언되어 있어서 불필요할 것 같다는 생각
     */
/*    public List<CommentDto.Response> getCommentList() {

    }*/
}
