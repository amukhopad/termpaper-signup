package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getEnum;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getString;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entities.enums.Faculty;
import ua.edu.ukma.termpapers.entities.users.Student;
import ua.edu.ukma.termpapers.repository.user.StudentRepository;

@Repository
public class DefaultStudentRepository extends AbstractUserRepository<Student>
    implements StudentRepository {

  public DefaultStudentRepository(HBaseConnection connection) {
    super(connection);
  }

  @Override
  public void put(Student student) {
    Put operation = buildUserPut(student);
    operation.addColumn(STUDENT_CF, CONTACT_INFO, toBytes(student.getContactInfo()));
    operation.addColumn(STUDENT_CF, STUDENT_ID_NUM, toBytes(student.getStudentIdNumber()));
    userPut(operation);
  }

  @Override
  public Student get(String email) {
    Get operation = new Get(email.getBytes());
    operation.addFamily(COMMON_CF);
    operation.addFamily(STUDENT_CF);

    Result result = userGet(operation);

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
