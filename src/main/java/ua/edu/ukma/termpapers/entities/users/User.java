package ua.edu.ukma.termpapers.entities.users;

abstract public class User {
  private long id;
  private int drfo;
  private String givenName;
  private String familyName;
  private String fathersName;

  public String getFullName() {
    return String.format("%s %s %s", familyName, givenName, fathersName);
  }

  public String getShortNameWithInitials() {
    return String.format("%s %s. %s.", familyName,
            givenName.substring(0, 1),
            fathersName.substring(0, 1));
  }

  public long getId() {
    return id;
  }

  public User setId(long id) {
    this.id = id;
    return this;
  }

  public int getDrfo() {
    return drfo;
  }

  public User setDrfo(int drfo) {
    this.drfo = drfo;
    return this;
  }

  public String getGivenName() {
    return givenName;
  }

  public User setGivenName(String givenName) {
    this.givenName = givenName;
    return this;
  }

  public String getFamilyName() {
    return familyName;
  }

  public User setFamilyName(String familyName) {
    this.familyName = familyName;
    return this;
  }

  public String getFathersName() {
    return fathersName;
  }

  public User setFathersName(String fathersName) {
    this.fathersName = fathersName;
    return this;
  }
}
