package ua.edu.ukma.termpapers.entities.enums;

public enum Category implements Localizable {
  HIGHEST("cпеціаліст вищої категорії"),
  FIRST("cпеціаліст першої категорії"),
  SECOND("cпеціаліст другої категорії"),
  SPECIALIST("cпеціаліст");

  private String name;

  Category(String name) {
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
