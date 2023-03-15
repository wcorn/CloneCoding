package com.base.project.domain.authorization.repository;

import com.base.project.domain.authorization.domain.PasswordLogin;
import com.base.project.domain.authorization.domain.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PasswordLoginRepository extends JpaRepository<PasswordLogin,Long> {

}
