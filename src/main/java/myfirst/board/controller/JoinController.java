package myfirst.board.controller;

import myfirst.board.domain.Member;
import myfirst.board.dto.MemberDto;
import myfirst.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class JoinController {

    @Autowired
    MemberService memberService;

    @GetMapping("/members")
    public String createForm() {
        return "members/join";
    }

    @PostMapping("/members/join")
    public String createMember(Member member) {
        memberService.join(member);
        return "home";
    }

}
