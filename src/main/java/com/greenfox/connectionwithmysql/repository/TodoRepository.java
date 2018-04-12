package com.greenfox.connectionwithmysql.repository;


import com.greenfox.connectionwithmysql.models.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

  List<Todo> findAllByDoneOrderById(boolean active);

  List<Todo> findAllByTitleNotNullOrderById();

  @Query("select t from Todo t where LOWER(t.title) like %:search%")
  List<Todo> findTodoByTitle(@Param("search") String search);
}
