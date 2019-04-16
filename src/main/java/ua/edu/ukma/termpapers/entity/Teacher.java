package ua.edu.ukma.termpapers.entity;

import ua.edu.ukma.termpapers.annotation.Column;
import ua.edu.ukma.termpapers.annotation.Id;
import ua.edu.ukma.termpapers.annotation.Table;
import ua.edu.ukma.termpapers.entity.enums.AcademicRole;
import ua.edu.ukma.termpapers.entity.enums.Degree;

@Table(name = "teachers")
public class Teacher {

  @Id
  private String id;
  @Column(family = "teachers", name = "userEmail")
  private User user;
  @Column(family = "teachers", name = "academicRole")
  private AcademicRole academicRole;
  @Column(family = "teachers", name = "degree")
  private Degree degree;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public AcademicRole getAcademicRole() {
    return academicRole;
  }

  public void setAcademicRole(AcademicRole academicRole) {
    this.academicRole = academicRole;
  }

  public Degree getDegree() {
    return degree;
  }

  public void setDegree(Degree degree) {
    this.degree = degree;
  }

  @Override
  public String toString() {
    return "Teacher{" +
        "id='" + id + '\'' +
        ", user=" + user.getFullName() +
        ", academicRole=" + academicRole +
        ", degree=" + degree +
        '}';
  }
}
