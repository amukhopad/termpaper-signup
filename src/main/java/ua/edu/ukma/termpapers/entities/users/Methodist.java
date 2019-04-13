package ua.edu.ukma.termpapers.entities.users;

import ua.edu.ukma.termpapers.entities.degrees.Category;

public class Methodist extends User {
  private Category category;

  public Category getCategory() {
    return category;
  }

  public Methodist setCategory(Category category) {
    this.category = category;
    return this;
  }
}
