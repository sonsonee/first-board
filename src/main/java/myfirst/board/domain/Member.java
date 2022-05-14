package myfirst.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;    //  Primary Key

    private String loginId;

    private String password;
    private String nickname;
    private String email;
    private LocalDateTime joinDate;

    protected Member() {
    }

    public Member(String loginId, String password, String nickname, String email) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.joinDate = LocalDateTime.now();
    }
}
