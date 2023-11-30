package com.example.servicediary.Service.rest;

import com.example.servicediary.Service.MentorService;
import com.example.servicediary.dao.MentorDao;
import com.example.servicediary.dto.rest.MentorReadRestDto;
import com.example.servicediary.dto.rest.MentorSaveRestDto;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.util.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Log4j2
public class MentorServiceImpl implements MentorService<MentorReadRestDto, MentorSaveRestDto> {

    private final MentorDao mentorDao;
    private final MapperUtils mapperUtils;


    @Override
    public List<MentorReadRestDto> findAllOrdered() {
        log.info("Возвращаем всех учителей");
        return mentorDao.findAll()
                .stream()
                .map(mapperUtils::mapToMentorReadRestDto)
                .toList();
    }

    @Override
    public List<MentorReadRestDto> getAll() {
        return mentorDao.findAllByOrderByIdAsc()
                .stream()
                .map(mapperUtils::mapToMentorReadRestDto)
                .toList();
    }

    @Override
    public Optional<MentorReadRestDto> findById(Integer id) {
        log.info("Возвращаем учителя с id: " + id);
        return mentorDao
                .findById(id)
                .map(mentor -> Optional.of(mapperUtils.mapToMentorReadRestDto(mentor)))
                .orElse(null);
    }

    @Override
    public void save(MentorSaveRestDto mentorSaveRestDto) {
        log.info("Добавляем нового учителя");
        final var newMentor = mapperUtils.mapToMentorSaveRest(mentorSaveRestDto);
        mentorDao.save(newMentor);
    }

    @Override
    public void update(MentorReadRestDto mentorReadRestDto, Integer id) {
        log.info("Редактируем учителя с id: " + id);
        Mentor mentor = Mentor.builder()
                .id(mentorReadRestDto.getId())
                .family(mentorReadRestDto.getFamily())
                .name(mentorReadRestDto.getName())
                .price(mentorReadRestDto.getPrice())
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
