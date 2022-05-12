package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.Member;
import myfirst.board.domain.service.LoginService;
import myfirst.board.web.form.LoginForm;
import myfirst.board.web.form.MemberCreateForm;
import myfirst.board.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/new")
    public String createMemberForm(Model model) {
        model.addAttribute("memberForm", new MemberCreateForm());
        return "members/join";
    }

    @PostMapping("/new")
    public String createMember(@Valid @ModelAttribute("member") MemberCreateForm form, BindingResult result) {

        if(result.hasErrors()){
            return"members/join";
        }

        //중복 검사

        //회원 가입
        Member member = new Member(form.getLoginId(), form.getPassword(),
                form.getNickname(), form.getEmail());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String createLoginForm(@ModelAttribute("loginForm")LoginForm form) {
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "members/login";
        }



        return "redirect:/";
    }


}
