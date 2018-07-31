package edu.msg.ro.persistence.user.dao;

import edu.msg.ro.persistence.user.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserManagement implements Management<User> {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;

    @Override
    public void addEntity(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public void updateEntity(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public void deleteEntity(User entity) {
        entityManager.remove(entity);
    }

    @Override
    public User getById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllEntities() {
        return entityManager.createNamedQuery("getAllUsers", User.class).getResultList();
    }
}
