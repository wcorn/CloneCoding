package com.base.project.tdd.domain.authorization.repository;

import com.base.project.tdd.domain.authorization.domain.PasswordLogin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PasswordLoginRepository extends JpaRepository<PasswordLogin,Long> {

}
