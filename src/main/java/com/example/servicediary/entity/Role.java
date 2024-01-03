package com.example.servicediary.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString(exclude = {"mentorRoles", "studentRoles", "usersRoles"})
@RequiredArgsConstructor
//@Data
public class Role implements GrantedAuthority{
//    ROLE_ADMIN, ROLE_MENTOR, ROLE_STUDENT;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "roles")
    private final List<Mentor> mentorRoles = new ArrayList<>();
    @OneToMany(mappedBy = "roles")
    private final List<Student> studentRoles = new ArrayList<>();
    @OneToMany(mappedBy = "roles")
    private final List<Users> usersRoles = new ArrayList<>();




    @Override
    public String getAuthority() {
        return getName();
    }
}
