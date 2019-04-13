package ua.edu.ukma.termpapers.entities.users;

public class Student extends User {
  private String studentIdNumber;
  private String contactInfo;

  public String getStudentIdNumber() {
    return studentIdNumber;
  }

  public Student setStudentIdNumber(String studentIdNumber) {
    this.studentIdNumber = studentIdNumber;
    return this;
  }

  public String getContactInfo() {
    return contactInfo;
  }

  public Student setContactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
    return this;
  }
}
