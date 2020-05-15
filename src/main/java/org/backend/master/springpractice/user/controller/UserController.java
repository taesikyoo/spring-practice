package org.backend.master.springpractice.user.controller;

import org.backend.master.springpractice.user.controller.dto.UserRequestDto;
import org.backend.master.springpractice.user.controller.dto.UserResponseDto;
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
        @RequestBody UserRequestDto userRequestDto) {
        userService.updateById(id, userRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
