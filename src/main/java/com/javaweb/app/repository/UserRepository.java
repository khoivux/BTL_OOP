package com.javaweb.app.repository;

import com.javaweb.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByEmail(String username);
    User findByUsername(String username);
    User getById(Long id);
}
