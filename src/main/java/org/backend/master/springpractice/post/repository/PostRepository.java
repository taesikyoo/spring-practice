package org.backend.master.springpractice.post.repository;

import org.backend.master.springpractice.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
