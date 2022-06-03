package myfirst.board.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class CommentController {

    @PostMapping("/post/{postId}")
    public String addComment(@PathVariable Long postId, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/post/{postId}";
    }

    @PutMapping("/post/{postId}")
    public String editComment() {
        return null;
    }

    @DeleteMapping("post/{postId}")
    public String deleteComment() {
        return null;
    }
}
