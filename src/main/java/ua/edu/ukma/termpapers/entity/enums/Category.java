package ua.edu.ukma.termpapers.entity.enums;

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
    return this.name();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
