package com.base.project.domain.user.repository;

import com.base.project.domain.user.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByEmail(String email);
}
