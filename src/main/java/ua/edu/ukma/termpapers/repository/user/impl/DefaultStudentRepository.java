package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getString;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.termpapers.entities.users.Student;
import ua.edu.ukma.termpapers.repository.user.StudentRepository;
import ua.edu.ukma.termpapers.repository.util.HbaseConnection;

@Repository
public class DefaultStudentRepository
        extends AbstractUserRepository<Student>
        implements StudentRepository {

  @Override
  public void put(Student student) throws IOException {
    HbaseConnection.put(getHbaseConf(), USERS_TABLE, () -> {
      Put put = commonPut(student);
      put.addColumn(STUDENT_CF, CONTACT_INFO, toBytes(student.getContactInfo()));
      put.addColumn(STUDENT_CF, STUDENT_ID_NUM, toBytes(student.getStudentIdNumber()));

      return put;
    });
  }

  @Override
  public Student get(String email) throws IOException {
    Result result = HbaseConnection.get(getHbaseConf(), USERS_TABLE, toBytes(email));
    return buildFromResult(result);
  }

  @Override
  public void delete(String email) throws IOException {
    super.delete(email);
  }

  private Student buildFromResult(Result result) {
    return new Student()
            .setEmail(Bytes.toString(result.getRow()))
            .setContactInfo(getString(result, STUDENT_CF, CONTACT_INFO))
            .setStudentIdNumber(getString(result, STUDENT_CF, STUDENT_ID_NUM))
            .setGivenName(getString(result, COMMON_CF, GIVEN_NAME))
            .setFathersName(getString(result, COMMON_CF, FATHER_NAME))
            .setFamilyName(getString(result, COMMON_CF, FAMILY_NAME))
            .setDrfo(getString(result, COMMON_CF, DRFO));
  }
}
