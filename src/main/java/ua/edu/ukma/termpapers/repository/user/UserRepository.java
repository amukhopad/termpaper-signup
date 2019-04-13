package ua.edu.ukma.termpapers.repository.user;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import java.io.IOException;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;

import ua.edu.ukma.termpapers.entities.users.User;

public interface UserRepository<U extends User> {
  TableName USERS_TABLE = TableName.valueOf("users");
  byte[] COMMON_CF = "common".getBytes();

  byte[] DRFO = "drfo".getBytes();
  byte[] GIVEN_NAME = "givenName".getBytes();
  byte[] FAMILY_NAME = "familyName".getBytes();
  byte[] FATHER_NAME = "fatherName".getBytes();

  void put(U user) throws IOException;

  U get(String email);

  void delete(String email);

  default Put commonData(User user) {
    Put put = new Put(toBytes(user.getEmail()));
    put.addColumn(COMMON_CF, GIVEN_NAME, toBytes(user.getGivenName()));
    put.addColumn(COMMON_CF, FAMILY_NAME, toBytes(user.getFamilyName()));
    put.addColumn(COMMON_CF, FATHER_NAME, toBytes(user.getFathersName()));
    put.addColumn(COMMON_CF, DRFO, toBytes(user.getDrfo()));

    return put;
  }
}
