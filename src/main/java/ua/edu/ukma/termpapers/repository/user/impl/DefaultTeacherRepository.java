package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;

import ua.edu.ukma.termpapers.entities.users.Teacher;
import ua.edu.ukma.termpapers.repository.user.TeacherRepository;
import ua.edu.ukma.termpapers.util.HbaseConnection;

public class DefaultTeacherRepository implements TeacherRepository {
  private Configuration hbaseConf;

  public DefaultTeacherRepository(Configuration hbaseConf) {
    this.hbaseConf = hbaseConf;
  }

  @Override
  public void put(Teacher teacher) throws IOException {
    HbaseConnection.put(hbaseConf, USERS_TABLE, () -> {
      Put put = commonData(teacher);
      put.addColumn(TEACHER_CF, DEGREE, toBytes(teacher.getDegree().name()));
      put.addColumn(TEACHER_CF, ACADEMIC_ROLE, toBytes(teacher.getAcademicRole().name()));

      return put;
    });
  }

  @Override
  public Teacher get(String email) {
    return null;
  }

  @Override
  public void delete(String email) {

  }
}
