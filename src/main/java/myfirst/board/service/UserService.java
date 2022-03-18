package myfirst.board.service;

import myfirst.board.domain.User;

public interface UserService {
    void join(User user);
    User findUser(Long userSn);
}
