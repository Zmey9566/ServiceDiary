package com.example.servicediary.Service.noRest;

import com.example.servicediary.dao.StudentDao;
import com.example.servicediary.security.LoginAuthorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Log4j2
public class StudentServiceImpl {

    private final StudentDao studentDao;

}
