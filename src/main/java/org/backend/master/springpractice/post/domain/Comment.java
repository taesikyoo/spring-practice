package org.backend.master.springpractice.post.domain;

import org.backend.master.springpractice.user.domain.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    private Long id;
    private String content;
//    private User author;
//    private Post post;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;
}
