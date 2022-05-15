package myfirst.board.web.controller;

import myfirst.board.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    /*@GetMapping("/")
    public String mainPage() {
        return "home"; // 렌더링할 html 파일 경로
    }
*/

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model) {

        model.addAttribute("member", loginMember);
        return "home";
    }
}
