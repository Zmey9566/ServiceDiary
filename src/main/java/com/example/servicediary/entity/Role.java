package com.example.servicediary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Data
public class Role implements GrantedAuthority{
//    ROLE_ADMIN, ROLE_MENTOR, ROLE_STUDENT;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "roles")
    private List<Mentor> mentorRoles = new ArrayList<>();
    @OneToMany(mappedBy = "roles")
    private List<Student> studentRoles = new ArrayList<>();


    @Override
    public String getAuthority() {
        return getName();
    }
}
