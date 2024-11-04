package com.javaweb.app.repository;

import com.javaweb.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
    User findByRole(String username);
}
