package com.example.servicediary.Service.noRest;

import com.example.servicediary.dao.UsersDao;
import com.example.servicediary.security.LoginAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements LoginAuthorization {

    private final UsersDao usersDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersDao.findByEmail(username);
    }
}
