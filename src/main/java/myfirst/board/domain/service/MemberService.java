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
     * @return Long memberId
     */
    public Long join(MemberDto.Request memberDto) {
        // DTO -> Entity 변환은 Service 계층에서 이루어짐
        Member savedMember = memberRepository.save(memberDto.toEntity());
        return savedMember.getId();
    }

    //아이디 중복 검사
    public void validateDuplicateLoginId(String loginId) {
        // 아이디 중복이면 예외 전달
        if(memberRepository.existsByLoginId(loginId))
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
    }

    /**
     * 로그인
     * @param loginId
     * @param password
     * @return memberId
     */
    public Long login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElseThrow(() -> new IllegalStateException("아이디와 비밀번호가 맞지 않습니다."))
                .getId();
    }

    /* 회원 탈퇴 */
    public void withdraw(Long memberId) {
        memberRepository.findById(memberId).ifPresent(m -> {
            m.update("탈퇴한 회원");
        });
    }

    public MemberDto.Response findById(Long memberId) {
        // Optional 값을 받아서 다시 Optional 값을 반환하는 것은 반복적인 일일 뿐이므로
        // Service 계층에서는 Optional.empty()를 받았을 때 이를 예외 처리하는 비즈니스 로직을 작성한다.
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException(String.format("#%d 회원이 없습니다.", memberId)));
        return new MemberDto.Response(member);
    }

    public MemberDto.Response findByLoginId(String loginId) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new IllegalStateException(String.format("아이디 %s의 회원이 없습니다.", loginId)));
        return new MemberDto.Response(member);
    }

}
