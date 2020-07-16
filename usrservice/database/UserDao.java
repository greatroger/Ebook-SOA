package com.example.usrservice.database;

import com.example.usrservice.entry.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<SystemUser, Integer> {
    SystemUser findByName(String name);
}
