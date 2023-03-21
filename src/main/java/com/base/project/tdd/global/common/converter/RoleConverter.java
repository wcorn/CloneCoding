package com.base.project.tdd.global.common.converter;

import com.base.project.tdd.domain.model.Role;
import org.springframework.core.convert.converter.Converter;
public class RoleConverter implements Converter<String, Role> {
    @Override
    public Role convert(String str) {
        return Role.valueOf(str.toUpperCase());
    }
}