package myfirst.board.domain.dto;

import lombok.*;
import myfirst.board.domain.entity.Member;
import myfirst.board.domain.entity.Post;
import org.apache.coyote.Response;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Convert;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Request {

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9-_]{3,10}",
                message = "아이디는 3~10자의 영문과 숫자, 특수기호(-),(_)만 사용 가능합니다.")
        private String loginId;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9-_!@#$%^&]{8,15}",
                message = "비밀번호는 8~15자의 영문과 숫자, 특수기호(-_!@#$%^&)만 사용 가능합니다.")
        private String password;

        @NotBlank
        private String nickname;

        @NotBlank
        @Email
        private String email;

        public Member toEntity() {
            return Member.builder()
                    .loginId(loginId)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .build();
        }
    }

    @Getter
    public static class Response {

        private Long id;
        private String loginId;
        private String nickname;
        private String email;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
//        private List<PostDto.Response> posts = new ArrayList<>();

        // Entity -> DTO
        public Response(Member member) {
            this.id = member.getId();
            this.loginId = member.getLoginId();
            this.nickname = member.getNickname();
            this.email = member.getEmail();
            this.createdDate = member.getCreatedDate();
            this.updatedDate = member.getUpdatedDate();
//            this.posts = member.getPosts().stream().map(PostDto.Response::new).collect(Collectors.toList());
        }

    }

/*
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9-_]{3,10}",
            message = "아이디는 3~10자의 영문과 숫자, 특수기호(-),(_)만 사용 가능합니다.")
    private String loginId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9-_!@#$%^&]{8,15}",
            message = "비밀번호는 8~15자의 영문과 숫자, 특수기호(-_!@#$%^&)만 사용 가능합니다.")
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    @Email
    private String email;

    private LocalDateTime joinDate;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .email(email).
                joinDate(joinDate).build();
    }*/
}
