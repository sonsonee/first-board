package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myfirst.board.domain.dto.CommentDto;
import myfirst.board.domain.service.CommentService;
import myfirst.board.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    public String addComment(@PathVariable Long postId,
                             @Valid @ModelAttribute("addComment") CommentDto.Request commentDto,
                             BindingResult bindingResult,
                             @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Long memberId,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("postId", postId);

        // 로그인하지 않고 댓글 작성 시도 시
        if (memberId == null) {
            return "redirect:/login";
        }

        // 댓글 내용이 비어있을 때
        if (bindingResult.hasErrors()) {
            return "redirect:/posts/post/{postId}";
        }

        commentService.comment(commentDto, memberId, postId);

        return "redirect:/posts/post/{postId}";
    }

    @PutMapping("/post/{postId}/comments/{commentId}")
    public String modifyComment(@PathVariable Long postId,
                              @PathVariable Long commentId,
                              @ModelAttribute CommentDto.Request dto,
                              RedirectAttributes redirectAttributes) {


        log.info("commentModify={}", dto);
        commentService.modify(commentId, dto);

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/post/{postId}";
    }

    @DeleteMapping("post/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable Long postId,
                                @PathVariable Long commentId,
                                @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = true) Long memberId,
                                RedirectAttributes redirectAttributes) {

        commentService.delete(commentId);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/post/{postId}";
    }
}
