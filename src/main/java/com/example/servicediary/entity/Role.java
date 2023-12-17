package com.example.servicediary.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    ROLE_ADMIN, ROLE_MENTOR, ROLE_STUDENT;

    @Override
    public String getAuthority() {
        return ROLE_ADMIN.name();
    }
}
