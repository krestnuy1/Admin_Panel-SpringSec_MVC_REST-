package ru.katsevich.spring.boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.katsevich.spring.boot_security.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);


}
