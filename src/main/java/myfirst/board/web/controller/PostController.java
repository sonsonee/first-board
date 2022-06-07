package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.CommentDto;
import myfirst.board.domain.dto.MemberDto;
import myfirst.board.domain.dto.PostDto;
import myfirst.board.domain.service.MemberService;
import myfirst.board.domain.service.PostService;
import myfirst.board.web.SessionConst;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.PrintWriter;

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
    public String viewPost(@PathVariable Long postId,
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Long loginMemberId,
                           @ModelAttribute("addComment") CommentDto.Request commentDto,
                           Model model) {

        if (loginMemberId != null) {
            MemberDto.Response member = memberService.findById(loginMemberId);
            model.addAttribute("memberId", member.getId());
        }

        PostDto.Response post = postService.findById(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "posts/post";
    }

    @GetMapping("/edit/{postId}")
    public String editPostForm(@PathVariable Long postId,
                               @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Long memberId,
                               Model model) {

        PostDto.Response post = postService.findById(postId);
        MemberDto.Response member = memberService.findById(memberId);

        //해당 게시글의 작성자가 아님 -> 홈 페이지로 돌려보냄
        if (!post.getMemberId().equals(member.getId())) {
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "posts/editPost";
    }

    @PutMapping("/edit/{postId}")
    public String editPost(@PathVariable Long postId, PostDto.Request dto, RedirectAttributes redirectAttributes) {
        postService.edit(postId, dto);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/post/{postId}";
    }

    @DeleteMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return "redirect:/";
    }
}
