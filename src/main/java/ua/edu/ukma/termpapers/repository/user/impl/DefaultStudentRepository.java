package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getEnum;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getString;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.termpapers.entities.enums.Faculty;
import ua.edu.ukma.termpapers.entities.users.Student;
import ua.edu.ukma.termpapers.repository.user.StudentRepository;
import ua.edu.ukma.termpapers.repository.util.HbaseConnection;

@Repository
public class DefaultStudentRepository
        extends AbstractUserRepository<Student>
        implements StudentRepository {

  @Override
  public void put(Student student) {
    userPut(student.getEmail(), p -> {
      Put put = commonPut(p, student);
      put.addColumn(STUDENT_CF, CONTACT_INFO, toBytes(student.getContactInfo()));
      put.addColumn(STUDENT_CF, STUDENT_ID_NUM, toBytes(student.getStudentIdNumber()));
      return put;
    });
  }


  @Override
  public Student get(String email) {
    Result result = userGet(email, get -> {
      get.addFamily(COMMON_CF);
      get.addFamily(STUDENT_CF);
      return get;
    });

    return buildFromResult(result);
  }

  @Override
  public void delete(String email) {
    super.delete(email);
  }

  private Student buildFromResult(Result result) {
    return (result.isEmpty()) ? null : new Student()
            .setEmail(Bytes.toString(result.getRow()))
            .setContactInfo(getString(result, STUDENT_CF, CONTACT_INFO))
            .setStudentIdNumber(getString(result, STUDENT_CF, STUDENT_ID_NUM))
            .setGivenName(getString(result, COMMON_CF, GIVEN_NAME))
            .setFathersName(getString(result, COMMON_CF, FATHER_NAME))
            .setFamilyName(getString(result, COMMON_CF, FAMILY_NAME))
            .setFaculty(getEnum(Faculty.class, result, COMMON_CF, FACULTY))
            .setDrfo(getString(result, COMMON_CF, DRFO));
  }
}
