package ua.edu.ukma.termpapers.repository.user;

import org.apache.hadoop.hbase.TableName;
import ua.edu.ukma.termpapers.entities.users.User;

public interface UserRepository<U extends User> {
  TableName USERS_TABLE = TableName.valueOf("users");
  byte[] COMMON_CF = "common".getBytes();

  byte[] DRFO = "drfo".getBytes();
  byte[] GIVEN_NAME = "givenName".getBytes();
  byte[] FAMILY_NAME = "familyName".getBytes();
  byte[] FATHER_NAME = "fathersName".getBytes();
  byte[] FACULTY = "faculty".getBytes();
  byte[] ROLE = "role".getBytes();


  void put(U user);

  U get(String email);

  void delete(String email);
}
