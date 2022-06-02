package myfirst.board.domain.service;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.PostDto;
import myfirst.board.domain.entity.Post;
import myfirst.board.domain.repository.MemberRepository;
import myfirst.board.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 포스팅
     */
    @Transactional
    public Long post(PostDto.Request postDto, Long memberId){
        Post post = postDto.toEntity(memberRepository.findById(memberId).get());
        postRepository.save(post);
        return post.getId();
    }

    @Transactional(readOnly = true)
    public PostDto.Response findById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException(String.format("#%d의 게시글이 없습니다.", postId)));
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
    @Transactional(readOnly = true)
    public List<PostDto.Response> getPostList() {
        List<PostDto.Response> postList = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            postList.add(new PostDto.Response(post));
        }
        return postList;
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public void edit(Long postId, PostDto.Request postDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException(String.format("#%d의 게시글이 없습니다.", postId)));
        post.update(postDto.getTitle(), postDto.getContent());
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException(String.format("#%d의 게시글이 없습니다.", postId)));

        postRepository.delete(post);
    }


}
