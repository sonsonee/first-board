package myfirst.board.domain.service;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.MemberDto;
import myfirst.board.domain.entity.Member;
import myfirst.board.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * MemberController에서는 Dto에만 의존하도록 하기 위해 Dto 객체만을 매개변수로 받고 반환한다.
     * @param memberDto
     * @return null : loginId 중복일 때
     * @return MemberDto.Response : 정상 가입
     */
    public MemberDto.Response join(MemberDto.Request memberDto) {

        // 중복이면 return null;
        try {
            validateDuplicateLoginId(memberDto.getLoginId());
        } catch (IllegalStateException e) {
            return null;
        }

        // DTO -> Entity 변환은 Service 계층에서 이루어짐
        Member savedMember = memberRepository.save(memberDto.toEntity());
        return new MemberDto.Response(savedMember);
    }

    //아이디 중복 검사
    public void validateDuplicateLoginId(String loginId) {
        // 아이디 중복이면 예외 전달
        if(memberRepository.existsByLoginId(loginId))
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
    }

    //로그인
    public String login(String loginId, String password) {
        if (memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null) == null) {
            return null;
        }
        return loginId;
    }


    public Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).get();
    }

}
