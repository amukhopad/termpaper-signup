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
import ua.edu.ukma.termpapers.entities.users.Teacher;
import ua.edu.ukma.termpapers.repository.coursework.CourseworkRepository;
import ua.edu.ukma.termpapers.repository.user.TeacherRepository;

@Controller
@RequestMapping("coursework")
public class CourseworkController {
  private final CourseworkRepository cwRepo;
  private final TeacherRepository teacherRepo;

  public CourseworkController(CourseworkRepository cwRepo, TeacherRepository teacherRepo) {
    this.cwRepo = cwRepo;
    this.teacherRepo = teacherRepo;
  }

  @GetMapping("/free")
  public String free(Model model) {
    List<Coursework> freeCWs = cwRepo.getFree();
    model.addAttribute("freeCWs", freeCWs);
    return "free-courseworks";
  }

  @GetMapping("/{id}")
  public String details(Model model, @PathVariable("id") String id) {
    Coursework cw = cwRepo.get(id);
    model.addAttribute("cw", cw);
    return "coursework-details";
  }

  @GetMapping("/new")
  public String registration(Model model) {
    model.addAttribute("coursework", new Coursework());
    model.addAttribute("faculties", Faculty.values());
    return "new-coursework";
  }

  @GetMapping("/student/{email}")
  public String getByTeacher(Model model, @PathVariable("email") String email) {
    List<Coursework> courseworkList = cwRepo.getByStudent(email);

    model.addAttribute("coursework", new Coursework());
    model.addAttribute("foundCWs", courseworkList);
    return "new-coursework";
  }

  @GetMapping("/teacher/{email}")
  public String getBy(Model model, @PathVariable("email") String email) {
    Teacher teacher = teacherRepo.get(email);
    List<Coursework> courseworkList = cwRepo.getByTeacher(email);

    model.addAttribute("teacher", teacher);
    model.addAttribute("foundCWs", courseworkList);
    return "teacher";
  }

  @ResponseBody
  @PostMapping("/submit")
  public String createUser(Model model, @ModelAttribute("coursework") Coursework coursework) {
    cwRepo.put(coursework);
    model.addAttribute("coursework", new Coursework());
    return "redirect:/coursework/new";
  }
}
