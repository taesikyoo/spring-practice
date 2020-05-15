package org.backend.master.springpractice.user.domain;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

public class UserTest {

    private User user;

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
}
