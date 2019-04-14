package ua.edu.ukma.termpapers.entities.enums;

public enum Faculty implements Localizable {
  LAW("факультет правничих наук"),
  HUMANITIES("факультет гуманітарних наук"),
  COMPUTER_SCIENCE("факультет інформатики"),
  NATURAL_SCIENCES("факультет природничих наук"),
  SOCIAL_SCIENCES("факультет соціальних наук та соціальних технологій"),
  ECONOMICS("Факультет економічних наук");


  private String name;

  Faculty(String name) {
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
