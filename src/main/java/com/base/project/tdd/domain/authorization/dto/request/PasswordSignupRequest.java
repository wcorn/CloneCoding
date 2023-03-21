package com.base.project.tdd.domain.authorization.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PasswordSignupRequest {
    private String email;
    private String password;
}
