package com.example.servicediary.mapper;

import com.example.servicediary.dto.StudentReadDto;
import com.example.servicediary.dto.StudentSaveDto;
import com.example.servicediary.entity.Student;
import org.springframework.stereotype.Controller;

@Controller
public class StudentMapper {

    StudentReadDto mapToStudentDto(Student student) {
        return StudentReadDto.builder()
                .id(student.getId())
                .name(student.getName())
                .family(student.getFamily())
                .level(student.getLevel())
                .build();
    }

    Student mapToStudent(StudentSaveDto studentSaveDto) {
        return Student.builder()
                .name(studentSaveDto.getName())
                .family(studentSaveDto.getFamily())
                .level(studentSaveDto.getLevel())
                .build();
    }
}
