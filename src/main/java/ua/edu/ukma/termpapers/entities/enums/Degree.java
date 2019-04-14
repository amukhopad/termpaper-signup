package ua.edu.ukma.termpapers.entities.enums;

public enum Degree implements Localizable {
  DOCTOR("доктор наук"),
  PHD("доктор філософії"),
  MASTER("магістр"),
  BACHELOR("бакалавр");

  private String name;

  Degree(String name) {
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
