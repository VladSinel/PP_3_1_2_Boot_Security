package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserDAO extends JpaRepository<User, Long> {

    User findByName(String name);

    User findUserById(Long id);
}
