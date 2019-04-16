package ua.edu.ukma.termpapers.entity.coursework;

import ua.edu.ukma.termpapers.entity.enums.Faculty;
import ua.edu.ukma.termpapers.entity.users.Student;
import ua.edu.ukma.termpapers.entity.users.Teacher;

public class Coursework {
  private String name;
  private String description;
  private int year;

  private Faculty faculty;
  private Teacher teacher;
  private Student student;

  private String studentEmail;
  private String teacherEmail;

  public String getName() {
    return name;
  }

  public Coursework setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Coursework setDescription(String description) {
    this.description = description;
    return this;
  }

  public int getYear() {
    return year;
  }

  public Coursework setYear(int year) {
    this.year = year;
    return this;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public Coursework setFaculty(Faculty faculty) {
    this.faculty = faculty;
    return this;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public Coursework setTeacher(Teacher teacher) {
    this.teacher = teacher;
    return this;
  }

  public Student getStudent() {
    return student;
  }

  public Coursework setStudent(Student student) {
    this.student = student;
    return this;
  }

  public String getStudentEmail() {
    return studentEmail;
  }

  public Coursework setStudentEmail(String studentEmail) {
    this.studentEmail = studentEmail;
    return this;
  }

  public String getTeacherEmail() {
    return teacherEmail;
  }

  public Coursework setTeacherEmail(String teacherEmail) {
    this.teacherEmail = teacherEmail;
    return this;
  }

  public String getKey() {
    return String.format("%d-%d-%d",
            this.getYear(),
            this.getTeacherEmail().hashCode(),
            this.getName().hashCode());
  }
}
