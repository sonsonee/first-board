package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.MemberDto;
import myfirst.board.domain.service.MemberService;
import myfirst.board.domain.service.PostService;
import myfirst.board.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Long loginMemberId, Model model) {

        if (loginMemberId != null) {
            MemberDto.Response loginMember = memberService.findById(loginMemberId);
            model.addAttribute("member", loginMember);
        }

        model.addAttribute("posts", postService.getPostList());

        return "home";
    }
}
