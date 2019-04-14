package ua.edu.ukma.termpapers.repository.user;

import ua.edu.ukma.termpapers.entities.users.Teacher;

public interface TeacherRepository extends UserRepository<Teacher>, CourseworkUser {
  byte[] TEACHER_CF = "teachers".getBytes();
  byte[] TEACHER_COURSEWORK_CF = "teacherCoursework".getBytes();

  byte[] ACADEMIC_ROLE = "academicRole".getBytes();
  byte[] DEGREE = "degree".getBytes();
}
