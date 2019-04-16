package ua.edu.ukma.termpapers.entity.users;

import ua.edu.ukma.termpapers.entity.enums.Faculty;
import ua.edu.ukma.termpapers.entity.enums.Role;

abstract public class User<T extends User<T>> {

  private String email;
  private String drfo;
  private String givenName;
  private String familyName;
  private String fathersName;
  private Faculty faculty;
  private Role role;

  public String getFullName() {
    return String.format("%s %s %s", familyName, givenName, fathersName);
  }

  public String getShortNameWithInitials() {
    return String.format("%s %s. %s.", familyName,
        givenName.substring(0, 1),
        fathersName.substring(0, 1));
  }

  public String getEmail() {
    return email;
  }

  public T setEmail(String email) {
    this.email = email;
    return (T) this;
  }

  public String getDrfo() {
    return drfo;
  }

  public T setDrfo(String drfo) {
    this.drfo = drfo;
    return (T) this;
  }

  public String getGivenName() {
    return givenName;
  }

  public T setGivenName(String givenName) {
    this.givenName = givenName;
    return (T) this;
  }

  public String getFamilyName() {
    return familyName;
  }

  public T setFamilyName(String familyName) {
    this.familyName = familyName;
    return (T) this;
  }

  public String getFathersName() {
    return fathersName;
  }

  public T setFathersName(String fathersName) {
    this.fathersName = fathersName;
    return (T) this;
  }

  public T setFaculty(Faculty faculty) {
    this.faculty = faculty;
    return (T) this;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public T setRole(Role role) {
    this.role = role;
    return (T) this;
  }

  public Role getRole() {
    return role;
  }
}
