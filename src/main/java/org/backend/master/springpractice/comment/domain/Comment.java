package org.backend.master.springpractice.comment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.backend.master.springpractice.post.domain.Post;
import org.backend.master.springpractice.user.domain.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;

    @Builder
    public Comment(String content, User author, Post post) {
        this.content = content;
        this.author = author;
        this.post = post;
        this.createdAt = LocalDateTime.now();
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void update(String content) {
        this.content = content;
        this.lastUpdatedAt = LocalDateTime.now();
    }
}
