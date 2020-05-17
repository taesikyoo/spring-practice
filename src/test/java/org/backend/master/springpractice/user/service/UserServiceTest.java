package org.backend.master.springpractice.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.backend.master.springpractice.user.domain.UserTest.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.backend.master.springpractice.user.controller.dto.UserRequestDto;
import org.backend.master.springpractice.user.controller.dto.UserResponseDto;
import org.backend.master.springpractice.user.domain.User;
import org.backend.master.springpractice.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void save() {
        User user = getUserFixture();
        given(userRepository.save(any(User.class))).willReturn(user);

        UserRequestDto userRequestDto = new UserRequestDto("jjj0611@daum.net", "password", "장재주");
        Long id = userService.createUser(userRequestDto);

        assertThat(id).isEqualTo(user.getId());
    }

    @Test
    void find() {
        User user = getUserFixture();
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));

        UserResponseDto foundUser = userService.readUserBy(1L);

        assertThat(foundUser.getEmail()).isEqualTo("jjj0611@daum.net");
        assertThat(foundUser.getName()).isEqualTo("장재주");
    }

    @Test
    void update() {
        UserRequestDto userRequestDto = new UserRequestDto("jjj0611@kiworkshop.org", "password2",
            "재주");
        User user = getUserFixture();
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        userService.updateById(1L, userRequestDto);
    }

    @Test
    void update_exception() {
        UserRequestDto userRequestDto = new UserRequestDto("jjj0611@kiworkshop.org", "password2",
            "재주");
        User user = getUserFixture();
        given(userRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> userService.updateById(1L, userRequestDto))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void delete() {
        given(userRepository.findById(anyLong())).willReturn(Optional.empty());

        userService.deleteById(1L);

        assertThatThrownBy(() -> userService.readUserBy(1L))
            .isInstanceOf(IllegalArgumentException.class);
    }
}