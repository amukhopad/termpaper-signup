package ua.edu.ukma.termpapers.repository.user;

public interface CourseworkUser {
  void putCoursework(String email, int year, String coursework);

  void deleteCoursework(String email, int year, String coursework);
}
