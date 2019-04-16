package ua.edu.ukma.termpapers.entity.enums;

public enum AcademicRole {
  PROFESSOR("професор"),
  DOCENT("доцент"),
  SR_TUTOR("старший викладач"),
  TUTOR("викладач"),
  TA("асистент");

  private String name;

  AcademicRole(String name) {
    this.name = name;
  }

  public String toString() {
    return this.name();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
