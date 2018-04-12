package com.greenfox.connectionwithmysql.controllers;

import com.greenfox.connectionwithmysql.models.Assignee;
import com.greenfox.connectionwithmysql.repository.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/assignee")
public class AssigneeController {

  @Autowired
  AssigneeRepository assigneeRepository;

  @GetMapping(value = {"/", "/list"})
  public String list(Model model){
      model.addAttribute("assignees", assigneeRepository.findAllByEmailNotNullOrderByName());
    return "listofassignees";
  }

  @GetMapping(value = "/create")
  public String createNew(Model model) {
    model.addAttribute("assignee", new Assignee());
    return "createassignee";
  }

  @PostMapping(value = "/create")
  public String saveNew(Assignee assignee) {
    assigneeRepository.save(assignee);
    return "redirect:/assignee/list";
  }

  @GetMapping(value = "/{id}/delete")
  public String delete(@PathVariable String id) {
    assigneeRepository.deleteById(Long.valueOf(id));
    return "redirect:/assignee/list";
  }

  @GetMapping(value = "/{id}/update")
  public String update(@PathVariable String id, Model model) {
    model.addAttribute("assignee", assigneeRepository.findById(Long.valueOf(id)).get());
    return "updateassignee";
  }

  @PostMapping(value = "/{id}/update")
  public String saveUpdated(@ModelAttribute Assignee assignee) {
    assigneeRepository.save(assignee);
    return "redirect:/assignee/list";
  }

  @GetMapping(value = "/search")
  public String search(@RequestParam(name = "search") String search, Model model) {
    if (assigneeRepository.findAssigneesByName(search.toLowerCase()) != null) {
      model.addAttribute("result",assigneeRepository.findAssigneesByName(search.toLowerCase()));
      return "searchedtodo";
    }
    return "redirect:/assignee/list";
  }
}
