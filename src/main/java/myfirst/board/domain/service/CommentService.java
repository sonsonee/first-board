package myfirst.board.domain.service;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.CommentDto;
import myfirst.board.domain.entity.Comment;
import myfirst.board.domain.repository.CommentRepository;
import myfirst.board.domain.repository.MemberRepository;
import myfirst.board.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    /**
     * 댓글 작성
     * @param commentDto
     * @param memberId
     * @param postId
     */
    @Transactional
    public void comment(CommentDto.Request commentDto, Long memberId, Long postId) {

        memberRepository.findById(memberId).ifPresent(m -> {
            postRepository.findById(postId).ifPresent(p -> {
                Comment comment = commentDto.toEntity(m, p);
                commentRepository.save(comment);
            });
        });
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public void modify(Long commentId, CommentDto.Request dto) {
        commentRepository.findById(commentId).ifPresent(c -> {
            c.update(dto.getContent());
        });
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public void delete(Long commentId) {
        commentRepository.findById(commentId).ifPresent(c -> {
            commentRepository.delete(c);
        });
    }

    /**
     * commentId로 찾기
     * @param commentId
     * @return CommentDto.Response
     */
    @Transactional(readOnly = true)
    public CommentDto.Response findById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException(String.format(("#%d 댓글이 없습니다."), commentId)));

        return new CommentDto.Response(comment);
    }

}
