package com.example.servicediary.Service.noRest;

import com.example.servicediary.Service.MentorService;
import com.example.servicediary.dao.MentorDao;
import com.example.servicediary.dao.StudentDao;
import com.example.servicediary.dao.UsersDao;
import com.example.servicediary.dto.noRest.MentorReadDto;
import com.example.servicediary.dto.noRest.MentorSaveDto;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import com.example.servicediary.entity.Users;
import com.example.servicediary.security.LoginAuthorization;
import com.example.servicediary.util.MapperUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class MentorServiceImpl implements MentorService<MentorReadDto, MentorSaveDto>{

    private final MentorDao mentorDao;
    private final UsersDao usersDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MapperUtils mapperUtils;

    @Override
    public List<MentorReadDto> findAllOrdered() {
        log.info("Возвращаем всех учителей");
        return mentorDao.findAll()
                .stream()
                .map(mapperUtils::mapToMentorReadDto)
                .sorted((m1, m2) -> Integer.compare(m1.getId(), m2.getId()))
                .toList();
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
        final var newUser = Users.builder()
                .email(newMentor.getEmail())
                .password(newMentor.getPassword())
                .roles(newMentor.getRoles())
                .build();
        mentorDao.save(newMentor);
        usersDao.save(newUser);
    }

    @Override
    public void update(MentorReadDto mentorReadDto, Integer id) {
        log.info("Редактируем учителя с id: " + id);
        final var oldPassword = mentorDao.findById(id).get().getPassword();
        Mentor mentor = mapperUtils.mapToMentorRead(mentorReadDto);

        if (!oldPassword.equals(mentor.getPassword())) {
            mentor.setPassword(bCryptPasswordEncoder.encode(mentor.getPassword()));}

        final var oldMentor = mentorDao.findById(id);
        final var userByEmail = usersDao.findByEmail(oldMentor.get().getEmail());

        userByEmail.setEmail(mentor.getEmail());
        userByEmail.setPassword(mentor.getPassword());
        userByEmail.setRoles(mentor.getRoles());

        mentorDao.save(mentor);
        usersDao.save(userByEmail);
    }

    @Override
    public void deleteById(int id) {
        log.info("Удаляем учителя с id: " + id);
        mentorDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.info("Очистка списка учителей");
        mentorDao.deleteAll();
    }

}
