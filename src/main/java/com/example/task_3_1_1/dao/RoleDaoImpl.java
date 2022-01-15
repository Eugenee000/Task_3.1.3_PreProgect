package com.example.task_3_1_1.dao;

import com.example.task_3_1_1.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role ").getResultList();
    }
}
