package org.backend.master.springpractice.comment.service;

import lombok.RequiredArgsConstructor;
import org.backend.master.springpractice.comment.controller.dto.CommentRequest;
import org.backend.master.springpractice.comment.controller.dto.CommentResponse;
import org.backend.master.springpractice.comment.domain.Comment;
import org.backend.master.springpractice.comment.repository.CommentRepository;
import org.backend.master.springpractice.comment.util.NameTagExtractor;
import org.backend.master.springpractice.post.domain.Post;
import org.backend.master.springpractice.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentResponse create(HttpSession session, Post post, CommentRequest request) {
        User author = (User) session.getAttribute("LOGIN_USER");
        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(author)
                .post(post)
                .build();

        Comment saved = commentRepository.save(comment);
        return getCommentResponse(saved);
    }

    public List<CommentResponse> getAll(Post post) {
        return post.getComments().stream()
                .map(this::getCommentResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse update(HttpSession httpSession, Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 comment가 존재하지 않습니다. id=" + id));

        validateLoginUser(httpSession, comment);

        comment.update(request.getContent());

        return getCommentResponse(comment);
    }

    public void delete(HttpSession httpSession, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 comment가 존재하지 않습니다. id=" + id));

        validateLoginUser(httpSession, comment);

        commentRepository.delete(comment);
    }

    private void validateLoginUser(HttpSession httpSession, Comment comment) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");
        User author = comment.getAuthor();
        if (loginUser == null) throw new IllegalArgumentException("로그인하지 않은 사용자입니다.");
        if (!(author.getId().equals(loginUser.getId()))) throw new IllegalArgumentException("comment를 삭제할 권한이 없습니다.");
    }

    private CommentResponse getCommentResponse(Comment comment) {
        List<String> nameTags = NameTagExtractor.extractNameTags(comment.getContent());

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .nameTags(nameTags)
                .authorName(comment.getAuthor().getName())
                .postId(comment.getPost().getId())
                .createdAt(comment.getCreatedAt())
                .lastUpdatedAt(comment.getLastUpdatedAt())
                .build();
    }
}
