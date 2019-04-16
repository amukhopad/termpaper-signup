package ua.edu.ukma.termpapers.controller;

import static org.apache.commons.lang3.Validate.notNull;

import java.security.Principal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ua.edu.ukma.termpapers.entity.Coursework;
import ua.edu.ukma.termpapers.entity.Student;
import ua.edu.ukma.termpapers.entity.Teacher;
import ua.edu.ukma.termpapers.entity.User;
import ua.edu.ukma.termpapers.entity.enums.Faculty;
import ua.edu.ukma.termpapers.repository.CourseworkRepository;
import ua.edu.ukma.termpapers.repository.StudentRepository;
import ua.edu.ukma.termpapers.repository.TeacherRepository;
import ua.edu.ukma.termpapers.repository.UserRepository;

@Controller
@RequestMapping("coursework")
public class CourseworkController {

  private final CourseworkRepository courseworkRepository;
  private final TeacherRepository teacherRepository;
  private final StudentRepository studentRepository;
  private final UserRepository userRepository;

  public CourseworkController(
      CourseworkRepository courseworkRepository,
      TeacherRepository teacherRepository,
      StudentRepository studentRepository,
      UserRepository userRepository) {
    this.courseworkRepository = courseworkRepository;
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/free")
  public String free(Model model) {
    List<Coursework> freeCWs = courseworkRepository.getFree();
    model.addAttribute("freeCWs", freeCWs);
    return "free-courseworks";
  }

  @GetMapping("/{id}")
  public String details(Principal principal, Model model, @PathVariable("id") String id) {
    String email = principal.getName();
    User user = userRepository.get(email);

    Coursework cw = courseworkRepository.get(id);

    model.addAttribute("user", user);
    model.addAttribute("cw", cw);
    return "coursework-details";
  }

  @GetMapping("/{id}/assign")
  public ModelAndView assign(Principal principal, @PathVariable("id") String id) {
    String email = principal.getName();

    Student current = studentRepository.getAll().stream()
        .filter(s -> StringUtils.equals(s.getUser().getEmail(), email))
        .findFirst().orElse(null);

    Coursework cw = courseworkRepository.get(id);
    cw.setStudent(current);

    courseworkRepository.put(cw);

    return new ModelAndView("redirect:/coursework/" + id);
  }

  @GetMapping("/{id}/unassign")
  public ModelAndView unassign(Principal principal, @PathVariable("id") String id) {
    String email = principal.getName();
    Coursework cw = courseworkRepository.get(id);

    if ((cw.getStudent().getUser().getEmail().equals(email))) {
      cw.setStudent(null);
    }
    
    courseworkRepository.put(cw);

    return new ModelAndView("redirect:/coursework/" + id);
  }

  @GetMapping("/new")
  public String registration(Model model) {
    List<Teacher> teachers = teacherRepository.getAll();
    model.addAttribute("coursework", new Coursework());
    model.addAttribute("teachers", teachers);
    model.addAttribute("faculties", Faculty.values());
    return "new-coursework";
  }

  @ResponseBody
  @PostMapping("/submit")
  public ModelAndView createCoursework(@ModelAttribute("coursework") Coursework coursework) {
    courseworkRepository.put(coursework);

    return new ModelAndView("redirect:/coursework/new");
  }
}
