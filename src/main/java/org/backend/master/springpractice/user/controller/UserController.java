package org.backend.master.springpractice.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.backend.master.springpractice.user.controller.dto.LoginRequest;
import org.backend.master.springpractice.user.controller.dto.UserRequestDto;
import org.backend.master.springpractice.user.controller.dto.UserResponseDto;
import org.backend.master.springpractice.user.controller.dto.UserUpdateRequestDto;
import org.backend.master.springpractice.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.readAll());
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserRequestDto userRequestDto) {
        Long id = userService.createUser(userRequestDto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserBy(@PathVariable Long id) {
        UserResponseDto user = userService.readUserBy(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
        @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.updateById(id, userUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(HttpSession session, @RequestBody LoginRequest loginRequest) {
        userService.login(session, loginRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        userService.logout(session);
        return ResponseEntity.ok().build();
    }

}
