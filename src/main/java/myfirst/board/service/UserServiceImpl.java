package myfirst.board.service;

import myfirst.board.domain.User;
import myfirst.board.repository.UserRepository;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void join(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUser(Long userSn) {
        return userRepository.findByUserSn(userSn).get();
    }
}
