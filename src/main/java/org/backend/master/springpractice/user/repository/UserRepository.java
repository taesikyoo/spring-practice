package org.backend.master.springpractice.user.repository;

import org.backend.master.springpractice.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
