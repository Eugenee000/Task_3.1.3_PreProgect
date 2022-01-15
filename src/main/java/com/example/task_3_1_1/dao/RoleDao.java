package com.example.task_3_1_1.dao;

import com.example.task_3_1_1.model.Role;

import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    Role getRoleByName(String name);

    List<Role> getAllRoles();
}
