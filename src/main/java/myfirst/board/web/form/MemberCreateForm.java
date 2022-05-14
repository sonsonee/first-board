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

    @NotNull
    @Length(min = 3, max = 10)
    private String loginId;

    @NotNull
    @Length(min = 8, max = 15)
    private String password;

    @NotEmpty
    private String nickname;

    @NotEmpty
    @Email
    private String email;

}