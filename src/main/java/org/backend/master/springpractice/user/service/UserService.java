package org.backend.master.springpractice.user.service;

import java.util.List;

import org.backend.master.springpractice.user.controller.dto.UserRequestDto;
import org.backend.master.springpractice.user.controller.dto.UserResponseDto;
import org.backend.master.springpractice.user.domain.User;
import org.backend.master.springpractice.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDto> readAll() {
        return UserResponseDto.listOf(userRepository.findAll());
    }

    public Long createUser(UserRequestDto userRequestDto) {
        User user = userRequestDto.toEntity();
        return userRepository.save(user).getId();
    }

    public UserResponseDto readUserBy(Long id) {
        return UserResponseDto.from(findById(id));
    }

    public void updateById(Long id, UserRequestDto userRequestDto) {
        User userToUpdate = userRequestDto.toEntity();
        User user = findById(id);
        user.update(userToUpdate);
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id입니다."));
    }
}
