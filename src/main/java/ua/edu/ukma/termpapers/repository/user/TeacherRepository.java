package ua.edu.ukma.termpapers.repository.user;

import ua.edu.ukma.termpapers.entity.users.Teacher;

public interface TeacherRepository extends UserRepository<Teacher> {
  byte[] TEACHER_CF = "teachers".getBytes();

  byte[] ACADEMIC_ROLE = "academicRole".getBytes();
  byte[] DEGREE = "degree".getBytes();
}
