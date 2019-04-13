package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.termpapers.entities.users.Student;
import ua.edu.ukma.termpapers.repository.user.StudentRepository;
import ua.edu.ukma.termpapers.util.HbaseConnection;

@Repository
public class DefaultStudentRepository implements StudentRepository {
  private Configuration hbaseConf;

  public DefaultStudentRepository(Configuration hbaseConf) {
    this.hbaseConf = hbaseConf;
  }

  @Override
  public void put(Student student) throws IOException {
    HbaseConnection.put(hbaseConf, USERS_TABLE, () -> {
      Put put = commonData(student);
      put.addColumn(STUDENT_CF, CONTACT_INFO, toBytes(student.getContactInfo()));
      put.addColumn(STUDENT_CF, STUDENT_ID_NUM, toBytes(student.getStudentIdNumber()));

      return put;
    });
  }

  @Override
  public Student get(String email) {
    return null;
  }

  @Override
  public void delete(String email) {

  }
}
