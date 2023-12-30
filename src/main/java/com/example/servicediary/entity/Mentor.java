package com.example.servicediary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"mentorStudentList","roles"})
@Builder
@Getter
@Table(name = "mentor")
public class Mentor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email
    private String email;
    @NonNull
    @Size(min = 6, message = "Некорректные данные в поле Пароль")
    private String password;
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Фамилия")
    @NonNull
    private String family;
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Имя")
    @NonNull
    private String name;
    private Long price;
    @OneToMany(mappedBy = "mentor")
    private final List<MentorStudent> mentorStudentList = new ArrayList<>();
    @ManyToOne
    private Role roles;

    public Mentor(@NonNull String family, @NonNull String name, Long price, String email) {
        this.family = family;
        this.name = name;
        this.price = price;
        this.email = email;
//        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return id == mentor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
