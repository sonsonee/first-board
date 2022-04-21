package myfirst.board.repository;

import myfirst.board.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Long save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
}
