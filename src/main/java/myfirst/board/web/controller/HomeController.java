package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.MemberDto;
import myfirst.board.domain.dto.PostDto;
import myfirst.board.domain.service.MemberService;
import myfirst.board.domain.service.PostService;
import myfirst.board.web.SessionConst;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Long loginMemberId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        //로그인 여부 체크
        if (loginMemberId != null) {
            MemberDto.Response loginMember = memberService.findById(loginMemberId);
            model.addAttribute("member", loginMember);
        }

        //페이징
        Page<PostDto.Response> posts = postService.getPostList(page);
        int startPage = Math.max(1, posts.getPageable().getPageNumber() - 1);
        int endPage = Math.min(posts.getTotalPages(), posts.getPageable().getPageNumber() + 3);
        int totalPage = posts.getTotalPages();

        model.addAttribute("posts", posts);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);

        LocalDateTime startOfDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate().atTime(LocalTime.MIN);
        model.addAttribute("startOfDate", startOfDate);

        return "home";
    }
}
