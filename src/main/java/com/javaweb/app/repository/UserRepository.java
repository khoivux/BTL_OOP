package com.javaweb.app.repository;

import com.javaweb.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String name);
    User findByUsername(String username);
    // Tìm kiếm người dùng theo ID
    User getById(Long id);
    User getByRole(String role);

    // Tìm kiếm người dùng theo tên
    List<User> findByUsernameContaining(String username);

    // Tìm kiếm người dùng theo email
    List<User> findByEmailContaining(String email);

    // Tìm kiếm người dùng bằng cách kết hợp tất cả các trường
    List<User> findByIdOrUsernameContainingOrEmailContaining(Long id, String username, String email);
}
