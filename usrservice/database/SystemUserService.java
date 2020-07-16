package com.example.usrservice.database;

import com.example.usrservice.entry.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SystemUserService {

    @Autowired
    private UserDao userDao;

    public SystemUser queryByUserName(String username) {
        return userDao.findByName(username);
    }
}
