package edu.msg.ro.persistence.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permissions {
    @Id
    private Integer idPermission;
    private PermissionType permissionType;
    private String description;

    public Integer getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Integer idPermission) {
        this.idPermission = idPermission;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
