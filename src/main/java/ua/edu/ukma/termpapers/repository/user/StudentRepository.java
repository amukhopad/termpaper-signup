package ua.edu.ukma.termpapers.repository.user;

import ua.edu.ukma.termpapers.entity.users.Student;

public interface StudentRepository extends UserRepository<Student> {
  byte[] STUDENT_CF = "students".getBytes();

  byte[] STUDENT_ID_NUM = "studentIdNumber".getBytes();
  byte[] CONTACT_INFO = "contactInfo".getBytes();
}
