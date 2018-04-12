package com.greenfox.connectionwithmysql.repository;


import com.greenfox.connectionwithmysql.models.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

  List<Todo> findAllByDoneOrderById(boolean active);

  List<Todo> findAllByTitleNotNullOrderById();

  Todo findTodoByTitle(String title);
}
