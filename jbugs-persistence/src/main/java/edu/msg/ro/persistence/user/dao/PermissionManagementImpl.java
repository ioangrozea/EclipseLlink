package edu.msg.ro.persistence.user.dao;

import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class PermissionManagementImpl implements PermissionManagement {

    @PersistenceContext(unitName = "jbugs-persistence")
    private
    EntityManager entityManager;

    @Override
    public Permission createPermission(Permission permission) {
        entityManager.persist(permission);
        return permission;
    }

    @Override
    public List<Permission> getAllPermission() {
        Query q = entityManager.createQuery("select P from Permission p");
        return q.getResultList();
    }

    @Override
    public Permission getPermissionById(long id) {
        return entityManager.find(Permission.class, id);
    }

    @Override
    public List<Permission> getPermissionByRole(Role role) {
        return null;
    }

    @Override
    public void createPermissionForRole(Role role, Permission permission) {

    }

    // TODO: 7/31/2018 if the remove does not work but the obj exists it will be still true returned
    @Override
    public boolean deletePermissionById(long id) {
        Optional<Permission> permissionById = Optional.ofNullable(getPermissionById(id));
        permissionById.ifPresent(x -> entityManager.remove(permissionById));
        return permissionById.isPresent();
    }

    @Override
    public boolean deletePermissionForRole(String type, Role role) {
        return false;
    }

    @Override
    public boolean deleteAllPermissionsForRole(Role role) {
        return false;
    }
}
