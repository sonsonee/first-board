package myfirst.board.web.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostForm {

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    private String content;

}
