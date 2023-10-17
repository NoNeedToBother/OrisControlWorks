package ru.kpfu.itis.paramonov.dao;

import java.util.List;

public interface Dao<T> {
    T get(int id);

    List<T> getAll();
}
