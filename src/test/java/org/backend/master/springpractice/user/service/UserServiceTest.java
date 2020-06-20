package org.backend.master.springpractice.user.service;

import org.backend.master.springpractice.user.controller.LoginRequest;
import org.backend.master.springpractice.user.controller.dto.UserRequestDto;
import org.backend.master.springpractice.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("정상 로그인")
    void loginTest() {
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
        assertThat(httpSession.getAttribute("LOGIN_USER")).isNotNull();
    }

    @Test
    @DisplayName("정상 로그아웃")
    void logout() {
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
        assertThat(httpSession.getAttribute("LOGIN_USER")).isNotNull();
        userService.logout(httpSession);
        assertThat(httpSession.getAttribute("LOGIN_USER")).isNull();
    }

    @Test
    @DisplayName("이미 로그인 되어있는 상태")
    void alreadyLogined() {
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
        assertThatThrownBy(() -> userService.login(httpSession, loginRequest))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("이미 로그인 되었습니다.");
    }

    @Test
    @DisplayName("비밀번호가 다를 때 실패")
    void wrongPassword() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
            .email("email")
            .password("password")
            .name("name")
            .build();
        Long id = userService.createUser(userRequestDto);

        LoginRequest loginRequest = LoginRequest.builder()
            .email("email")
            .password("wrong password")
            .build();

        assertThatThrownBy(() -> userService.login(httpSession, loginRequest))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못된 패스워드 입니다.");
    }

    @Test
    void name() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
            .email("email")
            .password("password")
            .name("name")
            .build();

        Long user = userService.createUser(userRequestDto);
        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("[유효성검사] 이메일이 존재하지 않을 때")
    void emailNotExists() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
            .email(null)
            .password("password")
            .name("name")
            .build();

        assertThatThrownBy(() -> userService.createUser(userRequestDto))
            .isInstanceOf(DataIntegrityViolationException.class);
    }
}
