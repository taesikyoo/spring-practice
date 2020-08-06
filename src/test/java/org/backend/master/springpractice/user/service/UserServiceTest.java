package org.backend.master.springpractice.user.service;


import org.backend.master.springpractice.user.domain.User;
import org.backend.master.springpractice.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void findUsersByNameTest() {
        //given
        User user1 = User.builder()
                .name("태식")
                .email("12321@email.com")
                .build();

        User user2 = User.builder()
                .name("정교")
                .email("1232123@email.com")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        List<String> userNames = new ArrayList<>();
        userNames.add(user1.getName());
        userNames.add(user2.getName());

        //when
        List<User> actualUsers = userService.findUsersByName(userNames);

        //then
        assertThat(actualUsers.size()).isEqualTo(2);
        assertThat(actualUsers.stream().map(User::getName).collect(Collectors.toList())).contains("태식");
        assertThat(actualUsers.stream().map(User::getName).collect(Collectors.toList())).contains("정교");
    }

    @Test
    void 유저이름을_찾지_못한_경우() {
        //given
        User user1 = User.builder()
                .name("태식")
                .email("12321@email.com")
                .build();

        User user2 = User.builder()
                .name("정교")
                .email("1232123@email.com")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        List<String> userNames = new ArrayList<>();
        userNames.add("광일");

        //when
        List<User> actualUsers = userService.findUsersByName(userNames);

        //then
        assertThat(actualUsers.size()).isZero();
        assertThat(actualUsers).isEmpty();
        System.out.println(actualUsers);
    }
}