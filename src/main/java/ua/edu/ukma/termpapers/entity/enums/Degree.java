package ua.edu.ukma.termpapers.entity.enums;

public enum Degree {
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

  public String toLocalizedString() {
    return name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
