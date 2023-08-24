package ru.katsevich.spring.boot_security.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.katsevich.spring.boot_security.entities.User;

import java.util.List;

@Service
public interface UserService extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<User> findRoleByRolesId(Long roleId);


}
