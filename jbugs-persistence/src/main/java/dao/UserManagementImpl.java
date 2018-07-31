package dao;

import edu.msg.ro.persistence.user.entity.Role;
import edu.msg.ro.persistence.user.entity.User;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class UserManagementImpl implements UserManagement {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "jbugs-persistence")
    EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User updateUser(User user) {
        em.merge(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
       // Query q = em.createQuery("select u from User u");
       // return q.getResultList();
        return null;
    }

    @Override
    public User getUserForUsername(String username) {
       // Query q = em.createQuery("select u from User u where u.username='" + username + "'");
       // return (User)q.getSingleResult();
        return null;
    }

    @Override
    public void deactivateUser(Long id) {
        User user = em.find(User.class,id);
        user.setActive(false);
    }

    @Override
    public void addRole(Role role) {

    }

    @Override
    public void removeRole(Role role) {

    }

    @Override
    public Role updateRole(Role role) {
        return null;
    }

    @Override
    public Role getRolerForId(Long id) {
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        return null;
    }
}
