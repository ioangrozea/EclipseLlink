package edu.msg.ro.persistence.user.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRole;
    private String type;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permissions;

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
