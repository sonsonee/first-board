package myfirst.board.controller;

import myfirst.board.domain.User;
import myfirst.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/joinUs.do", method = RequestMethod.POST)
    public String joinUs(User user) {
        userService.join(user);
        return "index";
    }
}
