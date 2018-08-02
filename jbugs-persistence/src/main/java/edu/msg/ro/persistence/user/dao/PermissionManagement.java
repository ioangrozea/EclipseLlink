package edu.msg.ro.persistence.user.dao;

import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.Role;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PermissionManagement {
    Permission createPermission(Permission permission);

    List getAllPermission();

    Permission getPermissionById(long id);

    List<Permission> getPermissionByRole(Role role);

    void createPermissionForRole(Role role, Permission permission);

    boolean deletePermissionById(long id);

    boolean deletePermissionForRole(String type, Role role);

    boolean deleteAllPermissionsForRole(Role role);
}
