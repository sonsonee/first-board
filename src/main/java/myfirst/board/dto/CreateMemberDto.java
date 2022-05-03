package myfirst.board.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class CreateMemberDto {

    @NotEmpty(message = "아이디 입력은 필수입니다")
    private String loginId;

    @NotEmpty(message = "비밀번호 입력은 필수입니다")
    private String password;

    @NotEmpty(message = "닉네임 입력은 필수입니다")
    private String nickname;

    @NotEmpty(message = "이메일 입력은 필수입니다")
    private String email;

    private LocalDateTime joinDate;

}
