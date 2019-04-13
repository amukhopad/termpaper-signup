package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getEnum;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getString;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import ua.edu.ukma.termpapers.entities.degrees.AcademicRole;
import ua.edu.ukma.termpapers.entities.degrees.Degree;
import ua.edu.ukma.termpapers.entities.users.Teacher;
import ua.edu.ukma.termpapers.repository.user.TeacherRepository;
import ua.edu.ukma.termpapers.repository.util.HbaseConnection;

public class DefaultTeacherRepository
        extends AbstractUserRepository<Teacher>
        implements TeacherRepository {

  @Override
  public void put(Teacher teacher) throws IOException {
    HbaseConnection.put(getHbaseConf(), USERS_TABLE, () -> {
      Put put = commonPut(teacher);
      put.addColumn(TEACHER_CF, DEGREE, toBytes(teacher.getDegree().name()));
      put.addColumn(TEACHER_CF, ACADEMIC_ROLE, toBytes(teacher.getAcademicRole().name()));

      return put;
    });
  }

  @Override
  public Teacher get(String email) throws IOException {
    Result result = HbaseConnection.get(getHbaseConf(), USERS_TABLE, toBytes(email));
    return buildFromResult(result);
  }

  @Override
  public void delete(String email) throws IOException {
    super.delete(email);
  }

  private Teacher buildFromResult(Result result) {
    return new Teacher()
            .setEmail(Bytes.toString(result.getRow()))
            .setAcademicRole(getEnum(AcademicRole.class, result, TEACHER_CF, ACADEMIC_ROLE))
            .setDegree(getEnum(Degree.class, result, TEACHER_CF, DEGREE))
            .setGivenName(getString(result, COMMON_CF, GIVEN_NAME))
            .setFathersName(getString(result, COMMON_CF, FATHER_NAME))
            .setFamilyName(getString(result, COMMON_CF, FAMILY_NAME))
            .setDrfo(getString(result, COMMON_CF, DRFO));
  }
}
