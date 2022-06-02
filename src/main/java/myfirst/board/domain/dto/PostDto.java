package myfirst.board.domain.dto;

import lombok.*;
import myfirst.board.domain.entity.Member;
import myfirst.board.domain.entity.Post;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {

        private Long id;

        @NotBlank
        @Size(max = 30)
        private String title;

        @NotBlank
        private String content;

        private Long views;
        private Member member;

        public Post toEntity(Member member) {
            return Post.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .views(0L)
                    .member(member).build();
        }
    }

    @Getter
    public static class Response {

        private Long id;
        private String title;
        private String content;
        private Long views;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private Long memberId;
        private String nickname;
        private List<CommentDto.Response> comments;

        public Response(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.views = post.getViews();
            this.createdDate = post.getCreatedDate();
            this.updatedDate = post.getUpdatedDate();
            this.memberId = post.getMember().getId();
            this.nickname = post.getMember().getNickname();
            this.comments = post.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
        }

    }



}
