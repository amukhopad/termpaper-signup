package ua.edu.ukma.termpapers.repository.user;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import java.io.IOException;

import org.apache.hadoop.hbase.TableName;

import ua.edu.ukma.termpapers.entities.users.User;

public interface UserRepository<U extends User> {
  TableName USERS_TABLE = TableName.valueOf("users");
  byte[] COMMON_CF = "common".getBytes();

  byte[] DRFO = "drfo".getBytes();
  byte[] GIVEN_NAME = "givenName".getBytes();
  byte[] FAMILY_NAME = "familyName".getBytes();
  byte[] FATHER_NAME = "fatherName".getBytes();
  byte[] FACULTY = "faculty".getBytes();


  void put(U user) throws IOException;

  U get(String email) throws IOException;

  void delete(String email) throws IOException;
}
