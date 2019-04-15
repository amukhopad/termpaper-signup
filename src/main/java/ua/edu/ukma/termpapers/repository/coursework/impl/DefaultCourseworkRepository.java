package ua.edu.ukma.termpapers.repository.coursework.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getEnum;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getInt;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getString;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.ifPresent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entities.coursework.Coursework;
import ua.edu.ukma.termpapers.entities.enums.Faculty;
import ua.edu.ukma.termpapers.entities.users.Student;
import ua.edu.ukma.termpapers.entities.users.Teacher;
import ua.edu.ukma.termpapers.repository.coursework.CourseworkRepository;
import ua.edu.ukma.termpapers.repository.user.StudentRepository;
import ua.edu.ukma.termpapers.repository.user.TeacherRepository;

@Repository
public class DefaultCourseworkRepository implements CourseworkRepository {

  private final HBaseConnection connection;
  private final TeacherRepository teacherRepo;
  private final StudentRepository studentRepo;

  public DefaultCourseworkRepository(
      HBaseConnection connection,
      TeacherRepository teacherRepo,
      StudentRepository studentRepo) {
    this.connection = connection;
    this.teacherRepo = teacherRepo;
    this.studentRepo = studentRepo;
  }

  @Override
  public void put(Coursework cw) {
    Put operation = new Put(cw.getKey().getBytes());
    ifPresent(operation, COURSEWORK_CF, NAME, cw.getName());
    ifPresent(operation, COURSEWORK_CF, DESCRIPTION, cw.getDescription());
    ifPresent(operation, COURSEWORK_CF, YEAR, cw.getYear());
    ifPresent(operation, COURSEWORK_CF, FACULTY, cw.getFaculty().name());
    ifPresent(operation, COURSEWORK_CF, STUDENT, cw.getStudentEmail());
    ifPresent(operation, COURSEWORK_CF, TEACHER, cw.getTeacherEmail());

    connection.put(COURSEWORK_TABLE, operation);
  }

  @Override
  public void delete(String key) {
    Delete operation = new Delete(key.getBytes());
    connection.delete(COURSEWORK_TABLE, operation);
  }

  @Override
  public Coursework get(String key) {
    Result result = connection.get(COURSEWORK_TABLE, key);

    Teacher teacher = teacherRepo.get(getString(result, COURSEWORK_CF, TEACHER));
    Student student = studentRepo.get(getString(result, COURSEWORK_CF, STUDENT));

    return buildFromResult(result, teacher, student);
  }

  @Override
  public List<Coursework> getByStudent(String studentEmail) {
    List<Result> results = findEquals(STUDENT, studentEmail);
    List<Coursework> courseworks = new ArrayList<>();
    if (results.isEmpty()) {
      return courseworks;
    }

    Student student = studentRepo.get(studentEmail);

    Map<String, Teacher> cache = new HashMap<>();
    for (Result r : results) {
      String teacherEmail = getString(r, COURSEWORK_CF, TEACHER);
      cache.putIfAbsent(studentEmail, teacherRepo.get(studentEmail));
      Teacher teacher = cache.get(teacherEmail);
      Coursework coursework = buildFromResult(r, teacher, student);
      courseworks.add(coursework);
    }

    return courseworks;
  }

  @Override
  public List<Coursework> getByTeacher(String teacherEmail) {
    List<Coursework> courseworks = new ArrayList<>();
    List<Result> results = findEquals(TEACHER, teacherEmail);
    if (results.isEmpty()) {
      return courseworks;
    }

    Teacher teacher = teacherRepo.get(teacherEmail);

    Map<String, Student> cache = new HashMap<>();
    for (Result r : results) {
      String studentEmail = getString(r, COURSEWORK_CF, STUDENT);
      cache.putIfAbsent(studentEmail, studentRepo.get(studentEmail));
      Student student = cache.get(studentEmail);
      Coursework coursework = buildFromResult(r, teacher, student);
      courseworks.add(coursework);
    }

    return courseworks;
  }

  private List<Result> findEquals(byte[] column, String value) {
    Filter filter = new SingleColumnValueFilter(
        COURSEWORK_CF, column,
        CompareOperator.EQUAL,
        toBytes(value));

    return connection.scan(COURSEWORK_TABLE, filter);
  }

  private Coursework buildFromResult(Result result, Teacher teacher, Student student) {
    return new Coursework()
        .setName(getString(result, COURSEWORK_CF, NAME))
        .setDescription(getString(result, COURSEWORK_CF, DESCRIPTION))
        .setYear(getInt(result, COURSEWORK_CF, YEAR))
        .setStudent(student)
        .setTeacher(teacher)
        .setStudentEmail((student == null) ? null : student.getEmail())
        .setTeacherEmail((teacher == null) ? null : teacher.getEmail())
        .setFaculty(getEnum(Faculty.class, result, COURSEWORK_CF, FACULTY));
  }
}
