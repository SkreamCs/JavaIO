package org.abdul.crudApp.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    List<T> getAll();

    T getById(ID id);

    void save(T t);

    void update(T t);

    void deleteById(ID id);
}
