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

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "home";
    }
}
