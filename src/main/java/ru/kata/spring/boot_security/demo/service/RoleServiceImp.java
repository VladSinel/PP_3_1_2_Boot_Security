package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.ArrayList;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImp(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Set<Role> getRoles(ArrayList<Long> roles) {
        return roleDAO.findByIdIn(roles);
    }


}
