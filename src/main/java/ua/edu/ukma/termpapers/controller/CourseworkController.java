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
import org.springframework.web.servlet.ModelAndView;
import ua.edu.ukma.termpapers.entity.Coursework;
import ua.edu.ukma.termpapers.entity.Teacher;
import ua.edu.ukma.termpapers.entity.enums.Faculty;
import ua.edu.ukma.termpapers.repository.CourseworkRepository;
import ua.edu.ukma.termpapers.repository.TeacherRepository;

@Controller
@RequestMapping("coursework")
public class CourseworkController {

  private final CourseworkRepository courseworkRepository;
  private final TeacherRepository teacherRepository;

  public CourseworkController(CourseworkRepository courseworkRepository,
      TeacherRepository teacherRepository) {
    this.courseworkRepository = courseworkRepository;
    this.teacherRepository = teacherRepository;
  }

  @GetMapping("/free")
  public String free(Model model) {
    List<Coursework> freeCWs = courseworkRepository.getFree();
    model.addAttribute("freeCWs", freeCWs);
    return "free-courseworks";
  }

  @GetMapping("/{id}")
  public String details(Model model, @PathVariable("id") String id) {
    Coursework cw = courseworkRepository.get(id);
    model.addAttribute("cw", cw);
    return "coursework-details";
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
