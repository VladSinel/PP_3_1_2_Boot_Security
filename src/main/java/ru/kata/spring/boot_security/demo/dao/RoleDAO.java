package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface RoleDAO extends JpaRepository<Role, Long> {

    Set<Role> findByIdIn(List<Long> id);

}
