package ua.edu.ukma.termpapers.entities.degrees;

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
    return name;
  }
}
