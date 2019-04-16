package ua.edu.ukma.termpapers.entity.enums;

public enum Role {
  STUDENT("Student"), TEACHER("Teacher"), METHODIST("Methodist");

  private String name;

  Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
