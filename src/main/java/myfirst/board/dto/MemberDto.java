package myfirst.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class MemberDto {

    private String loginId;
    private String nickname;
    private String email;
    private LocalDateTime joinDate;

}
