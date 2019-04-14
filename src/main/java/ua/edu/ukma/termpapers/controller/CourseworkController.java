package ua.edu.ukma.termpapers.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.edu.ukma.termpapers.entities.coursework.Coursework;
import ua.edu.ukma.termpapers.entities.enums.Faculty;
import ua.edu.ukma.termpapers.repository.coursework.CourseworkRepository;

@Controller
@RequestMapping("coursework")
public class CourseworkController {
  private final CourseworkRepository repository;

  public CourseworkController(CourseworkRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/new")
  public String registration(Model model) {
    model.addAttribute("coursework", new Coursework());
    model.addAttribute("faculties", Faculty.values());
    return "new-coursework";
  }

  @GetMapping("/student/{email}")
  public String getByTeacher(Model model, @PathVariable("email") String email) {
    List<Coursework> courseworkList = repository.getByStudent(email);

    model.addAttribute("coursework", new Coursework());
    model.addAttribute("foundCWs", courseworkList);
    return "new-coursework";
  }

  @GetMapping("/teacher/{email}")
  public String getBy(Model model, @PathVariable("email") String email) {
    List<Coursework> courseworkList = repository.getByTeacher(email);

    model.addAttribute("coursework", new Coursework());
    model.addAttribute("foundCWs", courseworkList);
    return "new-coursework";
  }

  @ResponseBody
  @PostMapping("/submit")
  public String createUser(Model model, @ModelAttribute("coursework") Coursework coursework) {
    repository.put(coursework);
    model.addAttribute("coursework", new Coursework());
    return "new-coursework";
  }
}
