package com.example.usrservice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyPasswordEncoder {
    public static BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
