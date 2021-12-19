package com.example.task_3_1_1.service;

import com.example.task_3_1_1.dao.RoleDao;
import com.example.task_3_1_1.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
