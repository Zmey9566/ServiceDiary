package com.example.servicediary.Service;

import com.example.servicediary.dao.MentorDao;
import com.example.servicediary.dto.MentorReadDto;
import com.example.servicediary.dto.MentorSaveDto;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.mapper.MentorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Log4j2
public class MentorServiceImpl implements MentorService{

    private final MentorDao mentorDao;
    private final MentorMapper mentorMapper;
//    private final MentorSaveMapper mentorSaveMapper;
//    private  final MentorSaveDto mentorSaveDto;

    @Override
    public List<MentorReadDto> findAll() {
        log.info("Возвращаем всех учителей");
        return mentorDao.findAll()
                .stream()
                .map(mentorMapper::mapToMentorDto)
                .toList();
    }

    @Override
    public List<MentorReadDto> getAllById() {
        log.info("Возвращаем всех учителей сортировка по id");
        return mentorDao.findAllByOrderByIdAsc()
                .stream()
                .map(mentorMapper::mapToMentorDto)
                .toList();
    }


    @Override
    public Optional<MentorReadDto> findById(Integer id) {
        log.info("Возвращаем учителя с id: " + id);
        return mentorDao
                .findById(id)
                .map(mentor -> Optional.of(mentorMapper.mapToMentorDto(mentor)))
                .orElse(null);
    }

    @Override
    public void save(MentorSaveDto mentorSaveDto) {
        log.info("Добавляем нового учителя");
        final var newMentor = mentorMapper.mapToMentor(mentorSaveDto);
        mentorDao.save(newMentor);
    }

    @Override
    public void update(MentorReadDto mentorReadDto, Integer id) {
        log.info("Редактируем учителя с id: " + id);
        Mentor mentor = Mentor.builder()
                .id(mentorReadDto.getId())
                .family(mentorReadDto.getFamily())
                .name(mentorReadDto.getName())
                .price(mentorReadDto.getPrice())
//                .students(mentorSaveDto.getStudents())
                .build();
        mentorDao.save(mentor);
    }

    @Override
    public void deleteById(int id) {
        log.info("Удаляем учителя с id: " + id);
        mentorDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.info("Удаляем всех учителей");
        mentorDao.deleteAll();
    }
}
