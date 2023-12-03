package com.example.servicediary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
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
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    private String email;

    @NonNull
    private String role;

    @Size(min = 2, max = 20, message = "Некорректные данные в поле Фамилия")
    @NonNull
    private String family;

    @Size(min = 2, max = 20, message = "Некорректные данные в поле Имя")
    @NonNull
    private String name;

    private Long price;

    @OneToMany(mappedBy = "mentor", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Student> students;

    public Mentor(@NonNull String family, @NonNull String name, Long price) {
        this.family = family;
        this.name = name;
        this.price = price;
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
