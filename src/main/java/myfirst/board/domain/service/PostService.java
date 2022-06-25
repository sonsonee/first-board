package myfirst.board.domain.service;

import lombok.RequiredArgsConstructor;
import myfirst.board.domain.dto.PostDto;
import myfirst.board.domain.entity.Post;
import myfirst.board.domain.repository.MemberRepository;
import myfirst.board.domain.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
    @Transactional
    public Long post(PostDto.Request postDto, Long memberId){
        Post post = postDto.toEntity(memberRepository.findById(memberId).get());
        postRepository.save(post);
        return post.getId();
    }

    /**
     * 포스트 리스트 얻기
     */
    @Transactional(readOnly = true)
    public Page<PostDto.Response> getPostList(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostDto.Response> postPage = posts.map(post -> new PostDto.Response(post));
        return postPage;
    }

    /**
     * 검색
     *  TODO post.id 기준 내림차순으로 출력하게 만들기 - Controller 에서 할수도
     * @param keyword
     * @return
     */
    public Page<PostDto.Response> search(String keyword, Pageable pageable) {
        Page<Post> foundPosts = postRepository.findByTitleContaining(keyword, pageable);
        Page<PostDto.Response> postPage = foundPosts.map(post -> new PostDto.Response(post));
        return postPage;
    }


    /**
     * 게시글 수정
     */
    @Transactional
    public void edit(Long postId, PostDto.Request postDto) {
        postRepository.findById(postId).ifPresent(p -> {
            p.update(postDto.getTitle(), postDto.getContent());
        });
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException(String.format("#%d 게시글이 존재하지 않습니다.", postId)));
        postRepository.delete(post);
    }

    /**
     * 조회수 업데이트
     */
    @Transactional
    public void increaseViews(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException(String.format("#%d 게시글이 존재하지 않습니다.", postId)));
        post.updateViews(post.getViews() + 1L);
    }


    // PostController 에서 사용
    @Transactional(readOnly = true)
    public PostDto.Response findById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException(String.format("#%d의 게시글이 없습니다.", postId)));
        return new PostDto.Response(post);
    }


}
