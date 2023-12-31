package com.example.servicediary.util;

import com.example.servicediary.dto.noRest.MentorReadDto;
import com.example.servicediary.dto.noRest.MentorSaveDto;
import com.example.servicediary.dto.noRest.StudentReadDto;
import com.example.servicediary.dto.noRest.StudentSaveDto;
import com.example.servicediary.dto.rest.MentorReadRestDto;
import com.example.servicediary.dto.rest.MentorSaveRestDto;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperUtils {
    private final ModelMapper modelMapper = new ModelMapper();

    public Mentor mapToMentorRead(MentorReadDto mentorReadDto){
        return modelMapper.map(mentorReadDto, Mentor.class);
    }

    public MentorReadDto mapToMentorReadDto(Mentor mentor) {
        return modelMapper.map(mentor, MentorReadDto.class);
    }

    public Mentor mapToMentorSave(MentorSaveDto mentorSaveDto){
        return modelMapper.map(mentorSaveDto, Mentor.class);
    }

    public MentorSaveDto mapToMentorSaveDto(Mentor mentor){
        return modelMapper.map(mentor, MentorSaveDto.class);
    }

    public Mentor mapToMentorReadRest(MentorReadRestDto mentorReadRestDto) {
        return modelMapper.map(mentorReadRestDto, Mentor.class);
    }

    public MentorReadRestDto mapToMentorReadRestDto(Mentor mentor) {
        return modelMapper.map(mentor, MentorReadRestDto.class);
    }

    public Mentor mapToMentorSaveRest(MentorSaveRestDto mentorSaveRestDto) {
        return modelMapper.map(mentorSaveRestDto, Mentor.class);
    }

    public MentorSaveRestDto mapToMentorSaveRestDto(Mentor mentor) {
        return modelMapper.map(mentor, MentorSaveRestDto.class);
    }

    public Student mapToStudentRead(StudentReadDto studentReadDto) {
        return modelMapper.map(studentReadDto, Student.class);
    }

    public StudentReadDto mapToStudentReadDto(Student student) {
        return modelMapper.map(student, StudentReadDto.class);
    }

    public Student mapToStudentSave(StudentSaveDto studentSaveDto) {
        return modelMapper.map(studentSaveDto, Student.class);
    }

    public StudentSaveDto mapToStudentSaveDto(Student student) {
        return modelMapper.map(student, StudentSaveDto.class);
    }
}
