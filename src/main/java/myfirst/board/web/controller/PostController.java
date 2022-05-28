package myfirst.board.web.controller;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.PostDto;
import myfirst.board.domain.entity.Member;
import myfirst.board.domain.entity.Post;
import myfirst.board.domain.repository.PostRepository;
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

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/write")
    public String writePostForm(@ModelAttribute("post") PostDto.Request dto) {
        return "posts/write";
    }

    @PostMapping("/write")
    public String writePost(@Valid @ModelAttribute("post") PostDto.Request dto, BindingResult bindingResult,
                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "posts/write";
        }

        //로그인한 멤버
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Post post = dto.toEntity(member);
//        postRepository.save(post);

        return "posts/post";
    }


}
