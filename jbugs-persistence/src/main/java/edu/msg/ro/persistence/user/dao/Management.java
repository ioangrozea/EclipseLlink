package edu.msg.ro.persistence.user.dao;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface Management <T> extends Serializable {
    void addEntity(T entity);

    void updateEntity(T entity);

    void deleteEntity(T entity);

    T getById(Integer id);

    List<T> getAllEntities();
}
