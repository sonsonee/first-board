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
    @Transactional
    public Long join(Member member) {
//        validateDuplicateLoginId(member);
        memberRepository.save(member);
        return member.getId();
    }


    //아이디 중복 검사
    private void validateDuplicateLoginId(Member member) {
        Optional<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if(!findMembers.isEmpty()){ //EXCEPTION
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }


}
