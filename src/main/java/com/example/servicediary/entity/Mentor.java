package com.example.servicediary.entity;

import jakarta.persistence.*;
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
    private String family;
    private String name;
    private BigDecimal price;
    @OneToMany(mappedBy = "mentor", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Student> students;

    public Mentor(String family, String name, BigDecimal price) {
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
