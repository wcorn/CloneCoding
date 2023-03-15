package com.base.project.domain.authorization.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PasswordSignupRequest {
    private String email;
    private String password;
}
