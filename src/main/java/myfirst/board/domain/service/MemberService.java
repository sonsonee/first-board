package myfirst.board.domain.service;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.Member;
import myfirst.board.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    public Long join(Member member) {
        if(memberRepository.findByLoginId(member.getLoginId()).isPresent()){ // 아이디 이미 존재하면 null 반환
            return null;
        }
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

/*    //아이디 중복 검사
    private void validateDuplicateLoginId(String loginId) {
        memberRepository.findByLoginId(loginId)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }*/


}
