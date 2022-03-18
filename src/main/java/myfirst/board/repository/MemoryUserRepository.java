package myfirst.board.repository;

import myfirst.board.domain.User;

import java.util.*;

public class MemoryUserRepository implements UserRepository{

    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) {
        user.setUserSn(++sequence);
        store.put(user.getUserSn(), user);
        return user;
    }

    @Override
    public Optional<User> findByUserSn(Long sn) {
        return Optional.ofNullable(store.get(sn));
    }

    @Override
    public Optional<User> findByUserId(String id) {
        return store.values().stream().filter(user -> user.getUserId().equals(id)).findAny();
    }

    @Override
    public Optional<User> findByUserName(String name) {
        return store.values().stream().filter(user -> user.getUserName().equals(name)).findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
}
