package ua.edu.ukma.termpapers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.edu.ukma.termpapers.entities.enums.Faculty;
import ua.edu.ukma.termpapers.entities.users.Student;
import ua.edu.ukma.termpapers.repository.user.StudentRepository;

@Controller
@RequestMapping("users")
public class UserController {
  private final StudentRepository repository;

  public UserController(StudentRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/{email}")
  public String getUserById(@PathVariable("email") String email) {
    try {
      Student user = repository.get(email);

      return user.toString();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
  }

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("student", new Student());
    model.addAttribute("faculties", Faculty.values());
    return "register";
  }

  @ResponseBody
  @PostMapping("/register")
  public String createUser(@ModelAttribute("student") Student student) {
    repository.put(student);

    return getUserById(student.getEmail());
  }
}
