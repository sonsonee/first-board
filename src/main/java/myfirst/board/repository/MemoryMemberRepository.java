package myfirst.board.repository;

import myfirst.board.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    @Override
    public Long save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
