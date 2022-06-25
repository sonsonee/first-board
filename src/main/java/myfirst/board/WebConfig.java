package myfirst.board;

import myfirst.board.web.Interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .excludePathPatterns("/", "/members/new", "/login", "/logout",
                        // TODO post 상세보기 관련 URI 추가
                        "/posts/post/**", "/posts/search/**",
                        "/*.css", "/*.ico", "/error");
    }
}
