package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myfirst.board.domain.dto.MemberDto;
import myfirst.board.domain.service.MemberService;
import myfirst.board.web.SessionConst;
import org.springframework.stereotype.Controller;
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
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createMemberForm(@ModelAttribute("member") MemberDto.Request memberDto) {
        return "/members/join";
    }

    @PostMapping("/members/new")
    public String createMember(@Valid @ModelAttribute("member") MemberDto.Request memberDto, BindingResult bindingResult) {

        // TODO 중복 에러 미리 표현하는 방법은..?
        try {
            memberService.validateDuplicateLoginId(memberDto.getLoginId());
        } catch (IllegalStateException e){
            bindingResult.rejectValue("loginId", "duplicate", new Object[]{"아이디"}, null);
            return "members/join";
        }

        //유효성 처리
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/join";
        }

        //회원 가입
        MemberDto.Response joinMember = memberService.join(memberDto);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String createLoginForm(@ModelAttribute("member") MemberDto.Request memberDto) {
        return "/members/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("member") MemberDto.Request memberDto, BindingResult bindingResult, HttpServletRequest request) {

        //로그인 처리
        String loginResult = memberService.login(memberDto.getLoginId(), memberDto.getPassword());

        if (loginResult == null) {
            bindingResult.reject("loginFail", "아이디와 비밀번호가 맞지 않습니다.");
            return "/members/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, memberService.findByLoginId(loginResult));
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
