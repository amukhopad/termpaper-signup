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
import org.springframework.web.servlet.ModelAndView;
import ua.edu.ukma.termpapers.entity.User;
import ua.edu.ukma.termpapers.entity.enums.Role;
import ua.edu.ukma.termpapers.repository.UserRepository;

@Controller
@RequestMapping("user")
public class UserController {

  private final UserRepository repository;

  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public String getUser(@RequestParam String email, Model model) {
    User user = repository.get(email);
    model.addAttribute(user);

    return "user-details";
  }

  @GetMapping("/all")
  public String getAllUsers(Model model) {
    List<User> users = repository.getAll();
    model.addAttribute(users);

    return "users";
  }

  @GetMapping("/register")
  public String registration(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("roles", Role.values());
    return "user-register";
  }

  @ResponseBody
  @PostMapping("/register")
  public ModelAndView createUser(@ModelAttribute("user") User user) {
    repository.put(user);

    return new ModelAndView("redirect:/user?email=" + user.getEmail());
  }
}
