package myfirst.board.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;    //  Primary Key

    private String loginId;

    private String password;
    private String nickname;
    private String email;

//    @OneToMany(mappedBy = "member")
//    private List<Post> posts = new ArrayList<>();

}
