package myfirst.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;    //  Primary Key

    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private LocalDateTime joinDate;

    public void initMemberInfo(String loginId, String password, String nickname, String email, LocalDateTime joinDate) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.joinDate = joinDate;
    }
}
