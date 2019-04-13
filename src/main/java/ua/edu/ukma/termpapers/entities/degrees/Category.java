package ua.edu.ukma.termpapers.entities.degrees;

public enum Category {
  HIGHEST("cпеціаліст вищої категорії"),
  FIRST("cпеціаліст першої категорії"),
  SECOND("cпеціаліст другої категорії"),
  SPECIALIST("cпеціаліст");

  private String name;

  Category(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }
}
