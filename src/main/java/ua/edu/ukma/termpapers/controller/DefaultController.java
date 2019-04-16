package ua.edu.ukma.termpapers.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.edu.ukma.termpapers.entity.User;
import ua.edu.ukma.termpapers.repository.UserRepository;

@Controller
public class DefaultController {
  private final UserRepository repository;

  public DefaultController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public String index(Principal principal, Model model) {
    if (principal != null) {
      String email = principal.getName();
      User user = repository.get(email);
      model.addAttribute("user", user);
    }

    return "index";
  }

  @GetMapping("/login")
  public String login(Model model, String error, String logout) {
    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");

    return "login";
  }
}
