package com.example.servicediary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"mentor"})
@Data
@Builder
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String family;
    private String name;
    private String level;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    Mentor mentor;

    public Student(String family, String name, String level) {
        this.family = family;
        this.name = name;
        this.level = level;
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
