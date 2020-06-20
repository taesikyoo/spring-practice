package org.backend.master.springpractice.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    private User user;

    public static User getUserFixture() {
        User user = User.builder()
            .email("jjj0611@daum.net")
            .password("password")
            .name("장재주")
            .build();
        Field id = ReflectionUtils.findField(User.class, "id");
        ReflectionUtils.makeAccessible(id);
        ReflectionUtils.setField(id, user, 1L);
        return user;
    }

    @BeforeEach
    void setUp() {
        user = User.builder()
            .email("jjj0611@daum.net")
            .password("password")
            .name("장재주").build();
    }

    @Test
    void cretaeUser() {
        assertThat(user.getEmail()).isEqualTo("jjj0611@daum.net");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getName()).isEqualTo("장재주");
    }

    @Test
    void updateUser() {
        user.updateName("재주");
        assertThat(user.getName()).isEqualTo("재주");
        user.updatePassword("password2");
        assertThat(user.getPassword()).isEqualTo("password2");
    }

    @Test
    @DisplayName("생성자 유효성 검사: 이메일이 null")
    void CreateValidationEmailNull() {
        assertThatThrownBy(() -> {
            User build = User.builder()
                .email(null)
                .password("password")
                .name("name")
                .build();
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("email should not be empty");
    }

    @Test
    @DisplayName("생성자 유효성 검사: 이메일이 empty String")
    void CreateValidationEmailEmptyString() {
        assertThatThrownBy(() -> {
            User build = User.builder()
                .email("")
                .password("password")
                .name("name")
                .build();
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("email should not be empty");
    }
}
