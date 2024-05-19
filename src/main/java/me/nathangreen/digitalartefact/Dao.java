package me.nathangreen.digitalartefact;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> getById(String id);
    List<T> getAll();
    void add(T t);
    void save(T t);
    void delete(T t);
}
