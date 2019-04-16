package ua.edu.ukma.termpapers.entity;

import ua.edu.ukma.termpapers.annotation.Column;
import ua.edu.ukma.termpapers.annotation.Id;
import ua.edu.ukma.termpapers.annotation.Table;
import ua.edu.ukma.termpapers.entity.enums.Role;

@Table(name = "users")
public class User {

  @Id
  private String email;
  @Column(family = "common", name = "drfo")
  private String drfo;
  @Column(family = "common", name = "familyName")
  private String familyName;
  @Column(family = "common", name = "givenName")
  private String givenName;
  @Column(family = "common", name = "fathersName")
  private String fathersName;
  @Column(family = "common", name = "role")
  private Role role;
  @Column(family = "common", name = "password")
  private String password;

//  TableName USERS_TABLE = TableName.valueOf("users");
//  byte[] COMMON_CF = "common".getBytes();
//
//  byte[] DRFO = "drfo".getBytes();
//  byte[] GIVEN_NAME = "givenName".getBytes();
//  byte[] FAMILY_NAME = "familyName".getBytes();
//  byte[] FATHER_NAME = "fathersName".getBytes();
//  byte[] FACULTY = "faculty".getBytes();
//  byte[] ROLE = "role".getBytes();
//

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

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getDrfo() {
    return drfo;
  }

  public User setDrfo(String drfo) {
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

  public User setRole(Role role) {
    this.role = role;
    return this;
  }

  public Role getRole() {
    return role;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }
}
