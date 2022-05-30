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
    public Long post(PostDto.Request postDto, Long memberId){
        Post post = postDto.toEntity(memberRepository.findById(memberId).get());
        postRepository.save(post);
        return post.getId();
    }

    public PostDto.Response findById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException(String.format("#%d의 게시물이 없습니다.", postId)));
        return new PostDto.Response(post);
    }

    /** TODO 제목으로 검색
     *
     * @param keyword
     * @return
     */
/*    public List<PostDto.Response> search(String keyword) {

    }*/

    /**
     * 포스트 리스트 얻기
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
        Post deletePost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException(String.format("#%d의 게시물이 없습니다.", postId)));
        postRepository.delete(deletePost);
    }


}
