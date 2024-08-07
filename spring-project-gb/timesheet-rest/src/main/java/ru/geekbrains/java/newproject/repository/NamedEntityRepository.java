package ru.geekbrains.java.newproject.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface NamedEntityRepository<T, ID> extends Repository<T, ID> {
    List<T> findByName(String name);
    List<T> findAll();
}
