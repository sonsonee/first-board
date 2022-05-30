package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.MemberDto;
import myfirst.board.domain.dto.PostDto;
import myfirst.board.domain.service.MemberService;
import myfirst.board.domain.service.PostService;
import myfirst.board.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/write")
    public String writePostForm(@ModelAttribute("post") PostDto.Request dto) {
        return "posts/write";
    }

    @PostMapping("/write")
    public String writePost(@Valid @ModelAttribute("post") PostDto.Request dto, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {

        // 값이 유효하지 않으면
        if (bindingResult.hasErrors()) {
            return "posts/write";
        }

        //로그인한 멤버 정보
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);

        MemberDto.Response memberDto = memberService.findById(memberId);
        Long postId = postService.post(dto, memberDto.getId());

        redirectAttributes.addAttribute("postId", postId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/posts/post/{postId}";
    }

    @GetMapping("/post/{postId}")
    public String viewPost(@PathVariable Long postId, Model model) {
        PostDto.Response post = postService.findById(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "posts/post";
    }

    @GetMapping("/post/update/{postId}")
    public String updatePost(@PathVariable Long postId, Model model) {

        return null;
    }
}
