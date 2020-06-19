package org.backend.master.springpractice.post.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.backend.master.springpractice.like.LikeResponse;
import org.backend.master.springpractice.post.domain.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String authorName;
    private LikeResponse likeResponse;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    @Builder
    public PostResponseDto(Long id, String title, String content, String authorName, LikeResponse likeResponse, LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.likeResponse = likeResponse;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

}
