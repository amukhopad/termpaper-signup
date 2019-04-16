package ua.edu.ukma.termpapers.repository;

import java.util.List;
import ua.edu.ukma.termpapers.entity.Coursework;

public interface CourseworkRepository extends CrudRepository<Coursework> {

  List<Coursework> getFree();

  List<Coursework> getByStudentEmail(String email);

  List<Coursework> getByTeacherEmail(String email);

}
