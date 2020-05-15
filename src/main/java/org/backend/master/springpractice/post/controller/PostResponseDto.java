package org.backend.master.springpractice.post.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.backend.master.springpractice.post.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(),
            post.getCreatedAt(), post.getLastUpdatedAt());
    }

    public static List<PostResponseDto> listOf(List<Post> posts) {
        return posts.stream()
            .map(PostResponseDto::from)
            .collect(Collectors.toList());
    }
}
