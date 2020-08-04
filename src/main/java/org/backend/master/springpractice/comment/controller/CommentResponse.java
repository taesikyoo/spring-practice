package org.backend.master.springpractice.comment.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.backend.master.springpractice.post.controller.PostResponseDto;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponse {

    private Long id;
    private String content;
    private String authorName;
    private Long postId;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    @Builder
    public CommentResponse(Long id, String content, String authorName, Long postId , LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
        this.postId = postId;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
