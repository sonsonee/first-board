package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/new")
    public String createMemberForm(@ModelAttribute("member") MemberCreateForm form) {
        return "members/join";
    }

    @PostMapping("/new")
    public String createMember(@Valid @ModelAttribute("member") MemberCreateForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/join";
        }

        //회원 가입
        Member member = new Member(form.getLoginId(), form.getPassword(),
                form.getNickname(), form.getEmail());
        Long joinMember = memberService.join(member);

        //중복 처리
        if (joinMember == null) {
            bindingResult.rejectValue("loginId", "duplicate", new Object[]{"아이디"}, null);
            return "members/join";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String createLoginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "members/login";
        }

        //로그인 처리
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디와 비밀번호가 맞지 않습니다.");
            return "members/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);
        log.info("session={}", session.getId());

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
