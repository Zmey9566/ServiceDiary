package com.example.servicediary.Service;

import com.example.servicediary.dto.noRest.MentorReadDto;
import com.example.servicediary.dto.noRest.MentorSaveDto;
import com.example.servicediary.dto.rest.MentorReadRestDto;

import java.util.List;
import java.util.Optional;

public interface MentorService {

    List<MentorReadDto> findAll();
    List<MentorReadDto> getAllById();
    List<MentorReadRestDto> getAll();

    Optional<MentorReadDto> findById(Integer id);

    void save(MentorSaveDto mentorSaveDto);
    void update(MentorReadDto mentorReadDto, Integer id);

    void update2(MentorReadRestDto mentorReadRestDto, Integer id);

    void deleteById(int id);

    void deleteAll();


}
