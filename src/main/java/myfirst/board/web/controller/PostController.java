package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.Post;
import myfirst.board.domain.repository.PostRepository;
import myfirst.board.web.form.PostForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/write")
    public String writePostForm(@ModelAttribute("postForm") PostForm form) {
        return "posts/write";
    }

    @PostMapping("/write")
    public String writePost(@Valid @ModelAttribute("postForm") PostForm form, BindingResult bindingResult,
                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "posts/write";
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            //TODO 현재 로그인한 유저 정보 알기
//            new Post(form.getTitle(), form.getContent(), session.)

        }
//        postRepository.save();

        return "posts/post";
    }
}
