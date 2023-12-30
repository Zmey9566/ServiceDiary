package com.example.servicediary.Service.noRest;

import com.example.servicediary.Service.StudentService;
import com.example.servicediary.dao.StudentDao;
import com.example.servicediary.dao.UsersDao;
import com.example.servicediary.dto.noRest.StudentReadDto;
import com.example.servicediary.dto.noRest.StudentSaveDto;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import com.example.servicediary.entity.Users;
import com.example.servicediary.security.LoginAuthorization;
import com.example.servicediary.util.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Log4j2
public class StudentServiceImpl implements StudentService<StudentReadDto, StudentSaveDto> {

    private final StudentDao studentDao;
    private final MapperUtils mapperUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersDao usersDao;


    @Override
    public List<StudentReadDto> findAllOrdered() {
        log.info("Возвращаем всех студентов");
        return studentDao.findAll()
                .stream()
                .map(mapperUtils::mapToStudentReadDto)
                .sorted((m1, m2) -> Integer.compare(m1.getId(), m2.getId()))
                .toList();
    }

    @Override
    public List<StudentReadDto> getAll() {
        return studentDao.findAllByOrderByIdAsc()
                .stream()
                .map(mapperUtils :: mapToStudentReadDto)
                .toList();
    }

    @Override
    public Optional<StudentReadDto> findById(Integer id) {
        log.info("Возвращаем студента с id: " + id);
        return studentDao
                .findById(id)
                .map(student -> Optional.of(mapperUtils.mapToStudentReadDto(student)))
                .orElse(null);
    }

    @Override
    public void save(StudentSaveDto saveDto) {
        log.info("Добавляем нового учителя");
        saveDto.setPassword(bCryptPasswordEncoder.encode(saveDto.getPassword()));
        final var newStudent = mapperUtils.mapToStudentSave(saveDto);
        final var newUser = Users.builder()
                .email(newStudent.getEmail())
                .password(newStudent.getPassword())
                .roles(newStudent.getRoles())
                .build();
        studentDao.save(newStudent);
        usersDao.save(newUser);
    }

    @Override
    public void update(StudentReadDto readDto, Integer id) {
        log.info("Редактируем студента с id: " + id);
        final var oldPassword = studentDao.findById(id).get().getPassword();
        Student student = mapperUtils.mapToStudentRead(readDto);

        if (!oldPassword.equals(student.getPassword())) {
            student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));}

        final var oldStudent = studentDao.findById(id);
        final var userByEmail = usersDao.findByEmail(oldStudent.get().getEmail());

        userByEmail.setEmail(student.getEmail());
        userByEmail.setPassword(student.getPassword());
        userByEmail.setRoles(student.getRoles());

        studentDao.save(student);
        usersDao.save(userByEmail);
    }

    @Override
    public void deleteById(int id) {
        log.info("Удаляем студента с id: " + id);
        studentDao.deleteById(id);

    }

    @Override
    public void deleteAll() {
        log.info("Очистка списка студентов");
        studentDao.deleteAll();
    }
}
