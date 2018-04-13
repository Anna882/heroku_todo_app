package com.greenfox.connectionwithmysql.repository;


import com.greenfox.connectionwithmysql.models.Assignee;
import com.greenfox.connectionwithmysql.models.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

  List<Todo> findAllByDoneOrderById(boolean active);

  List<Todo> findAllByTitleNotNullOrderById();

  List<Todo> findAllByAssignee(Assignee assignee);

  @Query("select t from Todo t where LOWER(t.title) like %:search%")
  List<Todo> findTodoByTitle(@Param("search") String search);
}
