package org.backend.master.springpractice.user.controller.dto;

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
}
