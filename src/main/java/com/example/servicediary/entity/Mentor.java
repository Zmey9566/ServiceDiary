package com.example.servicediary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"students"})
@EqualsAndHashCode(exclude = {"students"})
@Builder
@Table(name = "mentor")
public class Mentor implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    private String email;

    @NonNull
    private String role;

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

    @OneToMany(mappedBy = "mentor", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Student> students;

    public Mentor(@NonNull String family, @NonNull String name, Long price, String email, String role) {
        this.family = family;
        this.name = name;
        this.price = price;
        this.email = email;
        this.role = role;
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

    public String getAuthority() {
        return Role.ROLE_ADMIN.name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(Role.ROLE_ADMIN);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
