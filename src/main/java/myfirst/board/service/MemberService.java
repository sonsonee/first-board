package myfirst.board.service;

import lombok.RequiredArgsConstructor;
import myfirst.board.AutoAppConfig;
import myfirst.board.domain.Member;
import myfirst.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateLoginId(member);
        memberRepository.save(member);
        return member.getId();
    }

    //아이디 중복 검사
    private void validateDuplicateLoginId(Member member) {
        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if(!findMembers.isEmpty()){ //EXCEPTION
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }


    //유효성 검사



}
