package org.backend.master.springpractice.post.service;

import org.backend.master.springpractice.post.controller.PostRequestDto;
import org.backend.master.springpractice.post.controller.PostResponseDto;
import org.backend.master.springpractice.user.controller.LoginRequest;
import org.backend.master.springpractice.user.controller.dto.UserRequestDto;
import org.backend.master.springpractice.user.repository.UserRepository;
import org.backend.master.springpractice.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private PostService postService;

    @Test
    void name() {
        getLoginUser();
        PostRequestDto postRequestDto = PostRequestDto.builder()
            .title("여기까지만")
            .content("합시다.")
            .build();

        Long id = postService.createPost(httpSession, postRequestDto);
        postService.likePost(httpSession, id);
        PostResponseDto postResponseDto = postService.readPost(id);
        assertThat(postResponseDto.getLikeResponse().getUsers()).hasSize(1);
    }

    private void getLoginUser() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
            .email("email")
            .password("password")
            .name("name")
            .build();
        Long id = userService.createUser(userRequestDto);

        LoginRequest loginRequest = LoginRequest.builder()
            .email("email")
            .password("password")
            .build();

        userService.login(httpSession, loginRequest);
    }
}