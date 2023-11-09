package com.example.servicediary.mapper;

import com.example.servicediary.dto.MentorReadDto;
import com.example.servicediary.dto.MentorSaveDto;
import com.example.servicediary.dto.StudentReadDto;
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

    private final StudentMapper studentReadMapper;
    //    private final StudentSaveMapper studentSaveMapper;

    public MentorReadDto mapToMentorDto(Mentor mentor) {
        List<StudentReadDto> mayBeStudent = Optional.ofNullable(mentor.getStudents())
                .map(this::mapList)
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

    public Mentor mapToMentor(MentorSaveDto mentorSaveDto) {
//        List<StudentSaveDto> mayBeStudent = Optional.ofNullable(mentorSaveDto.getStudents())
//                .map(this::mapList)
//                .orElse(null);

        return Mentor
                .builder()
                .family(mentorSaveDto.getFamily())
                .name(mentorSaveDto.getName())
                .price(mentorSaveDto.getPrice())
//                .students(mayBeStudent)
                .build();
    }

//    private List<StudentSaveDto> mapList(List<Student> studentList) {
//        List<StudentSaveDto> result = new ArrayList<>();
//
//        for (Student student : studentList) {
//            result.add(studentSaveMapper.map(student));
//        }
//
//        return result;
//    }

    private List<StudentReadDto> mapList(List<Student> studentList) {
        List<StudentReadDto> result = new ArrayList<>();

        for (Student student : studentList) {
            result.add(studentReadMapper.mapToStudentDto(student));
        }

        return result;
    }
}
