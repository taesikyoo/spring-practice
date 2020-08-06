package org.backend.master.springpractice.user.repository;

import java.util.List;
import java.util.Optional;

import org.backend.master.springpractice.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByNameIn(List<String> names);
}
