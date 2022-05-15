package myfirst.board.web.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MemberCreateForm {

    @NotBlank
    @Length(min = 3, max = 10)
    private String loginId;

    @NotBlank
    @Length(min = 8, max = 15)
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    @Email
    private String email;

}