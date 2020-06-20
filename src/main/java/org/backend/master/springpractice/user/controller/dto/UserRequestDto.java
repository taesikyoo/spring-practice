package org.backend.master.springpractice.user.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.backend.master.springpractice.user.domain.User;
import org.backend.master.springpractice.user.util.PasswordEncryptor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    @NotNull
    @Min(1)
    private String email;
    @NotBlank
    private String password;
    private String name;

    @Builder
    public UserRequestDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User toEntity() {
        return User.builder()
            .email(email)
            .password(PasswordEncryptor.encrypt(password))
            .name(name).build();
    }
}
