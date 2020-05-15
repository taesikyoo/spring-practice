package org.backend.master.springpractice.post.controller;

import org.backend.master.springpractice.post.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostRequestDto {

    private String title;
    private String content;

    public Post toEntity() {
        return Post.builder()
            .title(title)
            .content(content).build();
    }

}
