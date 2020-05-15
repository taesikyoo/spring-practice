package org.backend.master.springpractice.user.controller.dto;

import org.backend.master.springpractice.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {

    private String email;
    private String password;
    private String name;

    public User toEntity() {
        return User.builder()
            .email(email)
            .password(password)
            .name(name).build();
    }

}
