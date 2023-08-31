package ru.katsevich.spring.boot_security.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.katsevich.spring.boot_security.entities.User;



@Service
public interface UserService extends JpaRepository<User, Long> {

    User findByUsername(String username);



}
