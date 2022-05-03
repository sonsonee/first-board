package myfirst.board.controller;

import myfirst.board.domain.Member;
import myfirst.board.dto.CreateMemberDto;
import myfirst.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class JoinController {

    @Autowired
    MemberService memberService;

    @GetMapping("/join")
    public String createForm() {
        return "members/join";
    }

    @PostMapping("/join/new")
    public String createMember(@Valid CreateMemberDto dto, BindingResult result) {

        if(result.hasErrors()){
            return"/join";
        }

        Member member = new Member(dto.getLoginId(), dto.getPassword(), dto.getNickname(), dto.getEmail());

        memberService.join(member);
        return "redirect:/";
    }

}
