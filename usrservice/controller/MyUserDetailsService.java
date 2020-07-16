package com.example.usrservice.controller;

import com.example.usrservice.database.SystemUserService;
import com.example.usrservice.entry.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SystemUserService systemUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("登录，用户名：" + s);
        SystemUser systemUser = systemUserService.queryByUserName(s);
        if (systemUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //String password = new BCryptPasswordEncoder().encode(systemUser.getPassword());
        //System.out.println("加密后密码："+password);
        User user = new User(systemUser.getName(), systemUser.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(systemUser.getRole()));
        return user;
    }
}