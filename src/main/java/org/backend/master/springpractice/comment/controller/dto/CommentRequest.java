package org.backend.master.springpractice.comment.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {

    private String content;

    @Builder
    public CommentRequest(String content) {
        this.content = content;
    }
}
