package ua.edu.ukma.termpapers.entity;

import ua.edu.ukma.termpapers.annotation.Column;
import ua.edu.ukma.termpapers.annotation.Id;
import ua.edu.ukma.termpapers.annotation.Table;
import ua.edu.ukma.termpapers.entity.enums.Faculty;

@Table(name = "students")
public class Student {

  @Id
  private String studentId;
  @Column(family = "students", name = "userEmail")
  private User user;
  @Column(family = "students", name = "contactInfo")
  private String contactInfo;
  @Column(family = "students", name = "faculty")
  private Faculty faculty;

  public String getStudentId() {
    return studentId;
  }

  public Student setStudentId(String studentId) {
    this.studentId = studentId;
    return this;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getContactInfo() {
    return contactInfo;
  }

  public Student setContactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
    return this;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }

  @Override
  public String toString() {
    return "Student{" +
        "user='" + user + '\'' +
        ", studentId=" + studentId +
        ", contactInfo='" + contactInfo + '\'' +
        ", faculty=" + faculty +
        '}';
  }
}
