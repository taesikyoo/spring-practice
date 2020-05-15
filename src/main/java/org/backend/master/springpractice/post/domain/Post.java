package org.backend.master.springpractice.post.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.Assert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;

    @Builder
    private Post(String title, String content) {
        Assert.hasLength(title, "title should not be empty");
        Assert.hasLength(content, "content should not be empty");
        this.title = title;
        this.content = content;
    }

    public void update(Post postToUpdate) {
        this.title = postToUpdate.title;
        this.content = postToUpdate.content;
    }
}

