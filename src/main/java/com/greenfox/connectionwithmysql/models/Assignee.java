package com.greenfox.connectionwithmysql.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "assignees")
public class Assignee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String email;
  @OneToMany
  private List<Todo> todos;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Todo> getTodos() {
    return todos;
  }

  public void setTodos(List<Todo> todos) {
    this.todos = todos;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}