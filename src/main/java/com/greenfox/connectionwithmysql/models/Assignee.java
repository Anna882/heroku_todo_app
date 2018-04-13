package com.greenfox.connectionwithmysql.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "assignees")
public class Assignee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long assigneeId;
  private String name;
  private String email;
  @OneToMany(mappedBy = "assigneeId")
  private List<Todo> todos;

  public List<Todo> getTodos() {
    return todos;
  }

  public void setTodos(List<Todo> todos) {
    this.todos = todos;
  }

  public Long getId() {
    return assigneeId;
  }

  public void setId(Long id) {
    this.assigneeId = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
