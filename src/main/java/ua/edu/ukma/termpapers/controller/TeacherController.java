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
import ua.edu.ukma.termpapers.entity.Coursework;
import ua.edu.ukma.termpapers.entity.Teacher;
import ua.edu.ukma.termpapers.entity.User;
import ua.edu.ukma.termpapers.entity.enums.AcademicRole;
import ua.edu.ukma.termpapers.entity.enums.Degree;
import ua.edu.ukma.termpapers.repository.CourseworkRepository;
import ua.edu.ukma.termpapers.repository.TeacherRepository;
import ua.edu.ukma.termpapers.repository.UserRepository;

@Controller
@RequestMapping("teacher")
public class TeacherController {

  private final TeacherRepository repository;
  private final UserRepository userRepository;
  private final CourseworkRepository courseworkRepository;

  public TeacherController(TeacherRepository repository,
      UserRepository userRepository,
      CourseworkRepository courseworkRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.courseworkRepository = courseworkRepository;
  }

  @GetMapping
  public String getTeacherById(@RequestParam("teacherId") String teacherId, Model model) {
    Teacher teacher = repository.get(teacherId);
    model.addAttribute(teacher);
    List<Coursework> courseworks = courseworkRepository.getByTeacherId(teacherId);

    model.addAttribute("teacher", teacher);
    model.addAttribute("foundCWs", courseworks);

    return "teacher-details";
  }

  @GetMapping("/all")
  public String getAllTeachers(Model model) {
    List<Teacher> teachers = repository.getAll();
    model.addAttribute(teachers);

    return "teachers";
  }

  @GetMapping("/register")
  public String registration(Model model) {
    List<User> users = userRepository.getAll();
    model.addAttribute("teacher", new Teacher());
    model.addAttribute("users", users);
    model.addAttribute("degrees", Degree.values());
    model.addAttribute("academicRoles", AcademicRole.values());

    return "teacher-register";
  }

  @ResponseBody
  @PostMapping("/register")
  public ModelAndView createTeacher(@ModelAttribute("teacher") Teacher teacher) {
    repository.put(teacher);

    return new ModelAndView("redirect:/teacher?teacherId=" + teacher.getId());
  }
}
