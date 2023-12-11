package com.example.servicediary.Service.noRest;

import com.example.servicediary.Service.MentorService;
import com.example.servicediary.dao.MentorDao;
import com.example.servicediary.dto.noRest.MentorReadDto;
import com.example.servicediary.dto.noRest.MentorSaveDto;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.util.MapperUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class MentorServiceImpl implements MentorService<MentorReadDto, MentorSaveDto>, UserDetailsService {

    private final MentorDao mentorDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MapperUtils mapperUtils;

    @Override
    public List<MentorReadDto> findAllOrdered() {
        log.info("Возвращаем всех учителей");
                final var mentorReadDto = mentorDao.findAll()
                .stream()
                .map(mapperUtils::mapToMentorReadDto).
                        sorted((m1, m2) -> Integer.compare(m1.getId(), m2.getId()))
                .toList();
        return mentorReadDto;
    }

    @Override
    public List<MentorReadDto> getAll() {
        return mentorDao.findAllByOrderByIdAsc()
                .stream()
                .map(mapperUtils::mapToMentorReadDto)
                .toList();
    }

    @Override
    public Optional<MentorReadDto> findById(Integer id) {
        log.info("Возвращаем учителя с id: " + id);
        return mentorDao
                .findById(id)
                .map(mentor -> Optional.of(mapperUtils.mapToMentorReadDto(mentor)))
                .orElse(null);
    }

    @Override
    public void save(MentorSaveDto mentorSaveDto) {
        log.info("Добавляем нового учителя");
        mentorSaveDto.setPassword(bCryptPasswordEncoder.encode(mentorSaveDto.getPassword()));
        final var newMentor = mapperUtils.mapToMentorSave(mentorSaveDto);
        mentorDao.save(newMentor);
    }

    @Override
    public void update(MentorReadDto mentorReadDto, Integer id) {
        log.info("Редактируем учителя с id: " + id);
        mentorReadDto.setPassword(bCryptPasswordEncoder.encode(mentorReadDto.getPassword()));
        Mentor mentor = Mentor.builder()
                .id(mentorReadDto.getId())
                .family(mentorReadDto.getFamily())
                .name(mentorReadDto.getName())
                .price(mentorReadDto.getPrice())
//                .students(mentorSaveDto.getStudents())
                .email(mentorReadDto.getEmail())
                .password(mentorReadDto.getPassword())
                .role(mentorReadDto.getRole())
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return mentorDao.findByEmail(username);
    }
}
