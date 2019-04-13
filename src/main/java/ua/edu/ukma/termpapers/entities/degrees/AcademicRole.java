package ua.edu.ukma.termpapers.entities.degrees;

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
    return name;
  }
}
