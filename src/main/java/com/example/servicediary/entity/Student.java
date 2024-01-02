package com.example.servicediary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"mentorStudentList"})
@Builder
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    private String email;

//    @NonNull
//    private String role;

    @NonNull
    @Size(min = 6, message = "Некорректные данные в поле Пароль")
    private String password;

    private String family;

    private String name;

    private String level;

    @OneToMany(mappedBy = "student")
    private final List<MentorStudent> mentorStudentList = new ArrayList<>();

    @ManyToOne
    private Role roles;

    public Student(@NonNull String family, @NonNull String name, String level, String email) {
        this.family = family;
        this.name = name;
        this.level = level;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
