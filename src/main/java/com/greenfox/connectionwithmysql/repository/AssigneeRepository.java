package com.greenfox.connectionwithmysql.repository;

import com.greenfox.connectionwithmysql.models.Assignee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssigneeRepository extends CrudRepository<Assignee, Long>{

  List<Assignee> findAllByEmailNotNullOrderByName();

  @Query("select a from Assignee a where LOWER(a.name) like %:search%")
  List<Assignee> findAssigneesByName(@Param("search") String search);
}
