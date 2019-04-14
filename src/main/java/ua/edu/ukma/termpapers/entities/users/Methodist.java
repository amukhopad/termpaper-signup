package ua.edu.ukma.termpapers.entities.users;

import ua.edu.ukma.termpapers.entities.enums.Category;

public class Methodist extends User<Methodist> {
  private Category category;

  public Category getCategory() {
    return category;
  }

  public Methodist setCategory(Category category) {
    this.category = category;
    return this;
  }

  @Override
  public String toString() {
    return "Methodist{ " + getFullName() +
            ", category=" + category +
            '}';
  }
}
