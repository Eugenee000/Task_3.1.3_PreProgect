package com.example.task_3_1_1.service;

import com.example.task_3_1_1.dao.RoleDao;
import com.example.task_3_1_1.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
