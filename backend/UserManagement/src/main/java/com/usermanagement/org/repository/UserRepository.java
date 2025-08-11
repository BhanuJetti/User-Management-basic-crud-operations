package com.usermanagement.org.repository;

import com.usermanagement.org.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
