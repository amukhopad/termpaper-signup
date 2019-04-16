package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HBaseUtil.getString;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entity.users.Student;
import ua.edu.ukma.termpapers.repository.user.StudentRepository;

@Repository
public class OldDefaultStudentRepository extends AbstractUserRepository<Student>
    implements StudentRepository {

  public OldDefaultStudentRepository(HBaseConnection connection) {
    super(connection, Student.class);
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
    if (StringUtils.isEmpty(email)) {
      return null;
    }

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

  @Override
  protected Student buildFromResult(Result result) {
    return (result.isEmpty()) ? null : super.buildFromResult(result)
        .setContactInfo(getString(result, STUDENT_CF, CONTACT_INFO))
        .setStudentIdNumber(getString(result, STUDENT_CF, STUDENT_ID_NUM));
  }
}
