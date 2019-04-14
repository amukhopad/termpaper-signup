package ua.edu.ukma.termpapers.entities.enums;

public enum AcademicRole implements Localizable {
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

  @Override
  public String toLocalizedString() {
    return name;
  }
}
