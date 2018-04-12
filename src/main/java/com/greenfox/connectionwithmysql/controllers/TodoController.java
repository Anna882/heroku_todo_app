package com.greenfox.connectionwithmysql.controllers;

import com.greenfox.connectionwithmysql.models.Todo;
import com.greenfox.connectionwithmysql.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/todo")
public class TodoController {

  @Autowired
  private TodoRepository todoRepository;

  @GetMapping(value = {"/", "/list"})
  public String list(@RequestParam(value = "isActive", required = false) boolean filterIfActive, Model model){
    if (filterIfActive) {
      model.addAttribute("todos", todoRepository.findAllByDoneOrderById(filterIfActive));
    } else {
      model.addAttribute("todos", todoRepository.findAllByTitleNotNullOrderById());
    }
    return "todolist";
  }

  @GetMapping(value = "/create")
  public String createNew() {
    return "create";
  }

  @PostMapping(value = "/create")
  public String saveNew(@ModelAttribute(name = "text") String text) {
    todoRepository.save(new Todo(text));
    return "redirect:/todo/list";
  }

  @GetMapping(value = "/{id}/delete")
  public String delete(@PathVariable String id) {
    todoRepository.deleteById(Long.valueOf(id));
    return "redirect:/todo/list";
  }

  @GetMapping(value = "/{id}/update")
  public String update(@PathVariable String id, Model model) {
    model.addAttribute("todo", todoRepository.findById(Long.valueOf(id)).get());
    return "update";
  }

  @PostMapping(value = "/{id}/update")
  public String saveUpdated(@ModelAttribute Todo todo) {
    todoRepository.save(todo);
    return "redirect:/todo/list";
  }

  @GetMapping(value = "/search")
  public String search(@RequestParam(name = "search") String title, Model model) {
    if (todoRepository.findTodoByTitle(title) != null) {
      model.addAttribute("todo",todoRepository.findTodoByTitle(title));
      return "searchedtodo";
    }
    return "redirect:/todo/list";
  }
}
