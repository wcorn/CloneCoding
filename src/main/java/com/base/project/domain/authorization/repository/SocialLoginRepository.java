package com.base.project.domain.authorization.repository;

import com.base.project.domain.authorization.domain.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SocialLoginRepository extends JpaRepository<SocialLogin,Long> {

}
