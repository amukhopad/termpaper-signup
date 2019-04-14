package ua.edu.ukma.termpapers.repository.coursework;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.hbase.TableName;

import ua.edu.ukma.termpapers.entities.coursework.Coursework;

public interface CourseworkRepository {
  TableName COURSEWORK_TABLE = TableName.valueOf("coursework");

  byte[] COURSEWORK_CF = "coursework".getBytes();
  byte[] NAME = "name".getBytes();
  byte[] DESCRIPTION = "description".getBytes();
  byte[] YEAR = "year".getBytes();

  byte[] FACULTY = "faculty".getBytes();
  byte[] TEACHER = "teacher".getBytes();
  byte[] STUDENT = "student".getBytes();


  void put(Coursework coursework) throws IOException;

  Coursework get(String email) throws IOException;

  void delete(String email) throws IOException;

  List<Coursework> getByStudent(String studentEmail);

  List<Coursework> getByTeacher(String teacherEmail);
}
