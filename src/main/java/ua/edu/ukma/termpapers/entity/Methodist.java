package ua.edu.ukma.termpapers.entity;

import ua.edu.ukma.termpapers.annotation.Column;
import ua.edu.ukma.termpapers.annotation.Id;
import ua.edu.ukma.termpapers.annotation.Table;
import ua.edu.ukma.termpapers.entity.enums.Category;

@Table(name = "methodists")
public class Methodist {

  @Id
  private String id;
  @Column(family = "methodists", name = "category")
  private Category category;
  @Column(family = "methodists", name = "userEmail")
  private User user;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Category getCategory() {
    return category;
  }

  public Methodist setCategory(Category category) {
    this.category = category;
    return this;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Methodist{" +
        "id='" + id + '\'' +
        ", user=" + user.getFullName() +
        ", category=" + category +
        '}';
  }
}
