package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myfirst.board.domain.dto.MemberDto;
import myfirst.board.domain.service.MemberService;
import myfirst.board.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        return "members/join";
    }

    @PostMapping("/members/new")
    public String createMember(@Valid @ModelAttribute("member") MemberDto.Request memberDto, BindingResult bindingResult) {

        // 아이디 중복 에러 처리
        try {
            memberService.validateDuplicateLoginId(memberDto.getLoginId());
        } catch (IllegalStateException e){
            bindingResult.rejectValue("loginId", "duplicate", new Object[]{"아이디"}, e.getMessage());
            return "members/join";
        }

        //유효성 처리
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/join";
        }

        // 회원 가입
        memberService.join(memberDto);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String createLoginForm(@ModelAttribute("member") MemberDto.Request memberDto,
                                  @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) Long alreadyLoginMember) {
        // 이미 로그인된 멤버가 있으면 돌려보내기
        if (alreadyLoginMember != null) {
            return "redirect:/";
        }
        
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("member") MemberDto.Request memberDto, BindingResult bindingResult, HttpServletRequest request) {

        Long loginMemberId; // 로그인할 멤버 id

        try {
            loginMemberId = memberService.login(memberDto.getLoginId(), memberDto.getPassword());
        } catch (IllegalStateException e) {
            bindingResult.reject("loginFail", e.getMessage());
            return "members/login";
        }

        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMemberId);

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
