package ua.edu.ukma.termpapers.repository;

import java.util.List;

public interface CrudRepository<T> {

  void put(T entity);

  T get(String key);

  void delete(String key);

  List<T> getAll();

  List<T> getBySingleFieldValue(String fieldName, String fieldValue);
}