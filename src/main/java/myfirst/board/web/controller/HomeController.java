package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.entity.Member;
import myfirst.board.domain.service.PostService;
import myfirst.board.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    /*@GetMapping("/")
    public String mainPage() {
        return "home"; // 렌더링할 html 파일 경로
    }
*/

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        model.addAttribute("member", loginMember);
        model.addAttribute("posts", postService.getPostList());
//        model.addAttribute("posts", posts);

        return "home";
    }
}
