package ua.edu.ukma.termpapers.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ukma.termpapers.entities.users.Student;
import ua.edu.ukma.termpapers.repository.user.StudentRepository;

@RestController
@RequestMapping("users")
public class UserController {
  @Autowired
  private StudentRepository repository;

  @GetMapping("/{email}")
  public String getUserById(@PathVariable("email") String email) {
    try {
      Student user = repository.get(email);

      return user.toString();
    } catch (IOException e) {
      return e.getMessage();
    }

  }

  @ResponseBody
  @PostMapping(value = "/register", consumes = "application/json")
  public String createUser(@RequestBody Student student) throws IOException {
    repository.put(student);

    return "/hbase/check";
  }

}
