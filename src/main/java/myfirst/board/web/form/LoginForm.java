package myfirst.board.web.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginForm {

    @NotNull
    private String loginId;

    @NotNull
    private String password;

}
