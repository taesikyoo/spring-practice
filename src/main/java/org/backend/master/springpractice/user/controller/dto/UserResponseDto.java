package org.backend.master.springpractice.user.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.backend.master.springpractice.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String email;
    private String name;

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getId(), user.getEmail(), user.getName());
    }

    public static List<UserResponseDto> listOf(List<User> users) {
        return users.stream()
            .map(UserResponseDto::from)
            .collect(Collectors.toList());
    }
}
