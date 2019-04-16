package ua.edu.ukma.termpapers.entity;

import ua.edu.ukma.termpapers.annotation.Column;
import ua.edu.ukma.termpapers.annotation.Id;
import ua.edu.ukma.termpapers.annotation.Table;
import ua.edu.ukma.termpapers.entity.enums.Faculty;

@Table(name = "coursework")
public class Coursework {

  @Id
  private String id;
  @Column(family = "coursework", name = "name")
  private String name;
  @Column(family = "coursework", name = "description")
  private String description;
  @Column(family = "coursework", name = "year")
  private int year;
  @Column(family = "coursework", name = "faculty")
  private Faculty faculty;
  @Column(family = "coursework", name = "teacher")
  private Teacher teacher;
  @Column(family = "coursework", name = "student")
  private Student student;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  @Override
  public String toString() {
    return "Coursework{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", year=" + year +
        ", faculty=" + faculty +
        ", teacher=" + teacher.getUser().getFullName() +
        ", student=" + student.getUser().getFullName() +
        '}';
  }
}
