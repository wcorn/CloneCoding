package com.base.project.global.common.converter;

import com.base.project.domain.model.Role;
import org.springframework.core.convert.converter.Converter;
public class RoleConverter implements Converter<String, Role> {
    @Override
    public Role convert(String str) {
        return Role.valueOf(str.toUpperCase());
    }
}