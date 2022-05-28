package myfirst.board.domain.service;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.PostDto;
import myfirst.board.domain.entity.Post;
import myfirst.board.domain.repository.MemberRepository;
import myfirst.board.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 포스팅
     */
    public void post(PostDto.Request postDto, String nickname){
        Post post = postDto.toEntity(memberRepository.findByNickname(nickname).get());
        postRepository.save(post);
    }

    /**
     * TODO 포스트 리스트 얻기
     */
    public List<PostDto.Response> getPostList() {
        List<PostDto.Response> postList = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            postList.add(new PostDto.Response(post));
        }
        return postList;
    }

    /**
     * TODO 업데이트
     */
    public void update(PostDto.Request postDto) {

    }

    /**
     * 게시글 삭제
     */
    public void delete(Long postId) {
        Post deletePost = postRepository.findById(postId).get();
        postRepository.delete(deletePost);
    }


}
