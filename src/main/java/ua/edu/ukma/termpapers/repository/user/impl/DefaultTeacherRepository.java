package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getEnum;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entity.enums.AcademicRole;
import ua.edu.ukma.termpapers.entity.enums.Degree;
import ua.edu.ukma.termpapers.entity.users.Teacher;
import ua.edu.ukma.termpapers.repository.user.TeacherRepository;

@Repository
public class DefaultTeacherRepository
    extends AbstractUserRepository<Teacher>
    implements TeacherRepository {

  public DefaultTeacherRepository(HBaseConnection connection) {
    super(connection, Teacher.class);
  }

  @Override
  public void put(Teacher teacher) {
    Put operation = buildUserPut(teacher);
    operation.addColumn(TEACHER_CF, DEGREE, toBytes(teacher.getDegree().name()));
    operation.addColumn(TEACHER_CF, ACADEMIC_ROLE, toBytes(teacher.getAcademicRole().name()));

    userPut(operation);
  }

  @Override
  public Teacher get(String email) {
    Get operation = new Get(email.getBytes());
    operation.addFamily(COMMON_CF);
    operation.addFamily(TEACHER_CF);

    Result result = userGet(operation);
    return buildFromResult(result);
  }

  @Override
  public void delete(String email) {
    super.delete(email);
  }

  @Override
  protected Teacher buildFromResult(Result result) {
    return (result.isEmpty()) ? null : super.buildFromResult(result)
        .setAcademicRole(getEnum(AcademicRole.class, result, TEACHER_CF, ACADEMIC_ROLE))
        .setDegree(getEnum(Degree.class, result, TEACHER_CF, DEGREE));
  }
}
