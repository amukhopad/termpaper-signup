package ua.edu.ukma.termpapers.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.edu.ukma.termpapers.entity.User;
import ua.edu.ukma.termpapers.entity.enums.Role;
import ua.edu.ukma.termpapers.repository.UserRepository;

@Controller
@RequestMapping("user")
public class NewUserController {

  private final UserRepository repository;

  public NewUserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public String getUser(@RequestParam String email) {
    User user = repository.get(email);

    return user.toString();
  }

  @GetMapping("/all")
  public String getAllUsers() {
    List<User> users = repository.getAll();

    return users.toString();
  }

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("roles", Role.values());
    return "user-register";
  }

  @ResponseBody
  @PostMapping("/register")
  public String createUser(@ModelAttribute("user") User user) {
    repository.put(user);

    return getUser(user.getEmail());
  }
}
