package org.backend.master.springpractice.post.service;

import java.util.List;

import org.backend.master.springpractice.post.controller.PostRequestDto;
import org.backend.master.springpractice.post.controller.PostResponseDto;
import org.backend.master.springpractice.post.domain.Post;
import org.backend.master.springpractice.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> readAll() {
        return PostResponseDto.listOf(postRepository.findAll());
    }

    public Long createPost(PostRequestDto postRequestDto) {
        Post post = postRequestDto.toEntity();
        return postRepository.save(post).getId();
    }

    public PostResponseDto readPost(Long id) {
        return PostResponseDto.from(findById(id));
    }

    public void updatePost(Long id, PostRequestDto postRequestDto) {
        Post post = findById(id);
        Post postToUpdate = postRequestDto.toEntity();
        post.update(postToUpdate);
        postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private Post findById(Long id) {
        return postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post입니다."));
    }
}
