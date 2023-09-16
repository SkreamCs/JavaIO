package org.example;

import java.util.Map;

public interface GenericRepository<T, ID> {
    void save(T t);

    void delete(ID t);

    T findGsonObject(ID id);

    Map<ID, T> readJson();

    void writerJson(Map<ID, T> t);
}
