package org.backend.master.springpractice.user.repository;

import static org.assertj.core.api.Assertions.*;

import org.backend.master.springpractice.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void save() {
        User user = User.builder()
            .email("jjj0611@hanmail.net")
            .password("password")
            .name("장재주").build();
        userRepository.save(user);
        assertThat(userRepository.existsById(user.getId())).isTrue();
    }
}