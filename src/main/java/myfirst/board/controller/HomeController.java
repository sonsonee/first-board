package myfirst.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("data", "어서오세요!");
        return "index";
    }

    @GetMapping("/join")
    public String login() {
        return "join";
    }
}
