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
import ua.edu.ukma.termpapers.entity.Student;
import ua.edu.ukma.termpapers.entity.User;
import ua.edu.ukma.termpapers.entity.enums.Faculty;
import ua.edu.ukma.termpapers.repository.StudentRepository;
import ua.edu.ukma.termpapers.repository.UserRepository;

@Controller
@RequestMapping("student")
public class StudentController {

  private final StudentRepository repository;
  private final UserRepository userRepository;

  public StudentController(StudentRepository repository,
      UserRepository userRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
  }

  @GetMapping
  public String getStudentById(@RequestParam("studentId") String studentId, Model model) {
    Student student = repository.get(studentId);
    model.addAttribute(student);

    return "student-details";
  }

  @GetMapping("/all")
  public String getAllStudents(Model model) {
    List<Student> students = repository.getAll();
    model.addAttribute(students);

    return "students";
  }

  @GetMapping("/register")
  public String registration(Model model) {
    List<User> users = userRepository.getAll();
    model.addAttribute("student", new Student());
    model.addAttribute("users", users);
    model.addAttribute("faculties", Faculty.values());

    return "student-register";
  }

  @ResponseBody
  @PostMapping("/register")
  public ModelAndView createStudent(@ModelAttribute("student") Student student) {
    repository.put(student);

    return new ModelAndView("redirect:/student?studentId=" + student.getStudentId());
  }
}
