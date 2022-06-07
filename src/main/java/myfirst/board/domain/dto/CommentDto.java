package myfirst.board.domain.dto;

import lombok.*;
import myfirst.board.domain.entity.Comment;
import myfirst.board.domain.entity.Member;
import myfirst.board.domain.entity.Post;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long id;

        @NotBlank
        private String content;

        public Comment toEntity(Member member, Post post) {
            return Comment.builder()
                    .id(id)
                    .content(content)
                    .member(member)
                    .post(post)
                    .build();
        }
    }

    @Getter
    public static class Response {

        private Long id;
        private String content;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private Long memberId;
        private String nickname;

        public Response(Comment comment) {
            this.id = comment.getId();
            this.content = comment.getContent();
            this.createdDate = comment.getCreatedDate();
            this.updatedDate = comment.getUpdatedDate();
            this.memberId = comment.getMember().getId();
            this.nickname = comment.getMember().getNickname();
        }
    }

}
