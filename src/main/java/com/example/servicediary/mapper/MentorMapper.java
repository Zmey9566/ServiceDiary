package com.example.servicediary.mapper;

import com.example.servicediary.dto.MentorReadDto;
import com.example.servicediary.dto.MentorSaveDto;
import com.example.servicediary.dto.StudentReadDto;
import com.example.servicediary.dto.StudentSaveDto;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MentorMapper {

    private final StudentMapper studentMapper;

    public MentorReadDto mapToMentorDto(Mentor mentor) {
        List<StudentReadDto> mayBeStudent = Optional.ofNullable(mentor.getStudents())
                .map(this::mapToStudentDTOList)
                .orElse(null);

        return MentorReadDto
                .builder()
                .id(mentor.getId())
                .family(mentor.getFamily())
                .name(mentor.getName())
                .price(mentor.getPrice())
                .students(mayBeStudent)
                .build();
    }
    private List<StudentReadDto> mapToStudentDTOList(List<Student> studentList) {
        List<StudentReadDto> result = new ArrayList<>();

        for (Student student : studentList) {
            result.add(studentMapper.mapToStudentDto(student));
        }

        return result;
    }

    public Mentor mapToMentor(MentorSaveDto mentorSaveDto) {
        List<StudentSaveDto> mayBeStudent = Optional.ofNullable(mentorSaveDto.getStudents())
                .map(this::mapToStudentList)
                .orElse(null);

        return Mentor
                .builder()
                .family(mentorSaveDto.getFamily())
                .name(mentorSaveDto.getName())
                .price(mentorSaveDto.getPrice())
//                .students(mayBeStudent)
                .build();
    }

    private List<StudentSaveDto> mapToStudentList(List<StudentSaveDto> studentList) {
        List<StudentSaveDto> result = new ArrayList<>();

        for (StudentSaveDto student : studentList) {
            result.add(studentMapper.mapToStudent(student));
        }

        return result;
    }

}
