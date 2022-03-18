package myfirst.board;

import myfirst.board.repository.MemoryUserRepository;
import myfirst.board.repository.UserRepository;
import myfirst.board.service.UserService;
import myfirst.board.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }


}
