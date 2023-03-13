package com.base.project.domain.authorization;

import com.base.project.domain.authorization.domain.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LoginRepository extends JpaRepository<SocialLogin,Long> {

}
