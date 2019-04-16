package ua.edu.ukma.termpapers.entity.users;

public class Student extends User<Student> {
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

  @Override
  public String toString() {
    return "Student{ " + getFullName() +
            ", studentIdNumber='" + studentIdNumber + '\'' +
            ", contactInfo='" + contactInfo + '\'' +
            '}';
  }
}
