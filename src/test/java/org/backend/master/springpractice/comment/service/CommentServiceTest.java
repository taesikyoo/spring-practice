package org.backend.master.springpractice.comment.service;

import org.backend.master.springpractice.comment.controller.dto.CommentRequest;
import org.backend.master.springpractice.comment.controller.dto.CommentResponse;
import org.backend.master.springpractice.post.controller.PostRequestDto;
import org.backend.master.springpractice.post.domain.Post;
import org.backend.master.springpractice.post.service.PostService;
import org.backend.master.springpractice.user.controller.LoginRequest;
import org.backend.master.springpractice.user.controller.dto.UserRequestDto;
import org.backend.master.springpractice.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private HttpSession httpSession;

    @BeforeEach
    void setUp() {
        httpSession.invalidate();
    }

    @Test
    void 댓글달기() {
        //given
        String userEmail = "naruto1@leaf.town";
        String userPassword = "password";
        String userName = "naruto1";
        createLoginUser(userEmail, userPassword, userName);

        String postContent = "글입니다";
        String postTitle = "제목입니다";
        Post post = createPost(postContent, postTitle);

        CommentRequest commentRequest = CommentRequest.builder()
                .content("댓글 내용입니다")
                .build();

        //when
        CommentResponse commentResponse = commentService.create(httpSession, post, commentRequest);

        //then
        assertThat(commentResponse.getAuthorName()).isEqualTo(userName);
        assertThat(commentResponse.getContent()).isEqualTo("댓글 내용입니다");
        assertThat(commentResponse.getPostId()).isEqualTo(post.getId());
    }

    void createLoginUser(String userEmail, String userPassword, String userName) {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .email(userEmail)
                .password(userPassword)
                .name(userName)
                .build();

        userService.createUser(userRequestDto);

        LoginRequest loginRequest = LoginRequest.builder()
                .email(userEmail)
                .password(userPassword)
                .build();

        userService.login(httpSession, loginRequest);
    }

    Post createPost(String postContent, String postTitle) {
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .content(postContent)
                .title(postTitle)
                .build();

        Long postId = postService.createPost(httpSession, postRequestDto);
        Post post = postService.findById(postId);
        return post;
    }

    void createNotLoginUser(String userEmail, String userPassword, String userName) {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .email(userEmail)
                .password(userPassword)
                .name(userName)
                .build();

        Long userId = userService.createUser(userRequestDto);
    }
}