package myfirst.board.web.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginForm {

    @NotEmpty
    @NotBlank
    private String loginId;

    @NotEmpty
    @NotBlank
    private String password;

}
