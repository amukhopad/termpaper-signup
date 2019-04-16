package ua.edu.ukma.termpapers.entity.users;

import ua.edu.ukma.termpapers.entity.enums.AcademicRole;
import ua.edu.ukma.termpapers.entity.enums.Degree;

public class Teacher extends User<Teacher> {
  private AcademicRole academicRole;
  private Degree degree;

  public AcademicRole getAcademicRole() {
    return academicRole;
  }

  public Teacher setAcademicRole(AcademicRole academicRole) {
    this.academicRole = academicRole;
    return this;
  }

  public Degree getDegree() {
    return degree;
  }

  public Teacher setDegree(Degree degree) {
    this.degree = degree;
    return this;
  }

  @Override
  public String toString() {
    return "Teacher{ " + getFullName() +
            ", academicRole=" + academicRole +
            ", degree=" + degree +
            '}';
  }
}