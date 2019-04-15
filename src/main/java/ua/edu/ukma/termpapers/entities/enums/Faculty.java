package ua.edu.ukma.termpapers.entities.enums;

public enum Faculty {
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
