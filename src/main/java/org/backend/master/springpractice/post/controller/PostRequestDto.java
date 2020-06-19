package org.backend.master.springpractice.post.controller;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.backend.master.springpractice.post.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.backend.master.springpractice.user.domain.User;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;

    @Builder
    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(User author) {
        return Post.builder()
            .title(title)
            .content(content)
            .author(author)
            .build();
    }
}
