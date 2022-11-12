package jamy.blog.service;

import jamy.blog.dto.post.WritePostReq;
import jamy.blog.post.Post;
import jamy.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service // 해당 클래스를 Spring Container에 빈으로 등록 후, Spring MVC 서비스로 표시
@RequiredArgsConstructor // final 또는 @NotNull 이 붙은 필드의 생성자를 자동으로 생성해준다.
public class PostService {
    // Spring Container에 싱글톤으로 생성되고 관리되는, PostRepository Bean 을 의존성 주입 받는다.
    private final PostRepository postRepository;

    @Transactional // 해당 함수 종료 시, commit 또는 Rollback 수행 (트랜잭션 관리)
    public Post save(WritePostReq writePostReq) {
        Post post = Post.builder()
                .title(writePostReq.getTitle())
                .content(writePostReq.getContent()).build();
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    // JPA 변경감지(Database의 객체 필드값의 변경을 감지하는 내부 기능끔
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("id를 확인해주세요!!"));
    }

    @Transactional
    public Post updateById(Long id, WritePostReq writePostReq) {
        Post postEntity = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("id를 확인해주세요!!"));
        postEntity.setTitle(writePostReq.getTitle());
        postEntity.setContent(writePostReq.getContent());
        return postEntity;
    }

    @Transactional
    public String deleteById(Long id) {
        try {
            postRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("id를 확인해주세요!!");
        }
        return "ok";
    }


}