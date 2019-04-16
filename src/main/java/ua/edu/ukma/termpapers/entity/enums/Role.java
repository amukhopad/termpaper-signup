package ua.edu.ukma.termpapers.entity.enums;

public enum Role {
  STUDENT("студент"),
  TEACHER("викладач"),
  METHODIST("методист");

  private String name;

  Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
