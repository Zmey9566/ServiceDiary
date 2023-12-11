package com.example.servicediary.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    ADMIN, MENTOR, STUDENT;

    @Override
    public String getAuthority() {
        return ADMIN.name();
    }
}
