package myfirst.board.repository;

import myfirst.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUserSn(Long sn);
    Optional<User> findByUserId(String id);
    Optional<User> findByUserName(String name);
    List<User> findAll();
}
