package org.backend.master.springpractice.user.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = User.of("jjj0611@daum.net", "password", "장재주");
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
}
