package com.greenfox.connectionwithmysql.repository;


import com.greenfox.connectionwithmysql.models.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

  List<Todo> findAllByDoneOrderById(boolean active);

  List<Todo> findAllByTitleNotNullOrderById();

  @Query("select all from Todo where LOWER(title) LIKE LOWER('%search%')")
  List<Todo> findTodoByTitle(String search);
}
