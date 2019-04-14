package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getEnum;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getString;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import ua.edu.ukma.termpapers.entities.enums.AcademicRole;
import ua.edu.ukma.termpapers.entities.enums.Degree;
import ua.edu.ukma.termpapers.entities.enums.Faculty;
import ua.edu.ukma.termpapers.entities.users.Student;
import ua.edu.ukma.termpapers.entities.users.Teacher;
import ua.edu.ukma.termpapers.repository.user.TeacherRepository;
import ua.edu.ukma.termpapers.repository.util.HbaseConnection;

public class DefaultTeacherRepository
        extends AbstractUserRepository<Teacher>
        implements TeacherRepository {

  @Override
  public void put(Teacher teacher) {
    userPut(teacher.getEmail(), p -> {
      Put put = commonPut(p, teacher);
      put.addColumn(TEACHER_CF, DEGREE, toBytes(teacher.getDegree().name()));
      put.addColumn(TEACHER_CF, ACADEMIC_ROLE, toBytes(teacher.getAcademicRole().name()));

      return put;
    });
  }

  @Override
  public Teacher get(String email) {
    Result result = userGet(email, get -> {
      get.addFamily(COMMON_CF);
      get.addFamily(TEACHER_CF);
      return get;
    });
    return buildFromResult(result);
  }

  @Override
  public void delete(String email) {
    super.delete(email);
  }

  private Teacher buildFromResult(Result result) {
    return (result.isEmpty()) ? null : new Teacher()
            .setEmail(Bytes.toString(result.getRow()))
            .setAcademicRole(getEnum(AcademicRole.class, result, TEACHER_CF, ACADEMIC_ROLE))
            .setDegree(getEnum(Degree.class, result, TEACHER_CF, DEGREE))
            .setGivenName(getString(result, COMMON_CF, GIVEN_NAME))
            .setFathersName(getString(result, COMMON_CF, FATHER_NAME))
            .setFamilyName(getString(result, COMMON_CF, FAMILY_NAME))
            .setFaculty(getEnum(Faculty.class, result, COMMON_CF, FACULTY))
            .setDrfo(getString(result, COMMON_CF, DRFO));
  }
}
