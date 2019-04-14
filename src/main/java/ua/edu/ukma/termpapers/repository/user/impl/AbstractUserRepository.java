package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entities.users.User;
import ua.edu.ukma.termpapers.repository.user.UserRepository;

abstract public class AbstractUserRepository<U extends User> implements UserRepository<U> {

  private HBaseConnection connection;

  public AbstractUserRepository(HBaseConnection connection) {
    this.connection = connection;
  }

  @Override
  public void delete(String email) {
    Delete operation = new Delete(email.getBytes());
    userDelete(operation);
  }

  protected Put buildUserPut(User user) {
    Put operation = new Put(user.getEmail().getBytes());
    operation.addColumn(COMMON_CF, GIVEN_NAME, toBytes(user.getGivenName()));
    operation.addColumn(COMMON_CF, FAMILY_NAME, toBytes(user.getFamilyName()));
    operation.addColumn(COMMON_CF, FATHER_NAME, toBytes(user.getFathersName()));
    operation.addColumn(COMMON_CF, FACULTY, toBytes(user.getFaculty().name()));
    operation.addColumn(COMMON_CF, DRFO, toBytes(user.getDrfo()));
    return operation;
  }

  protected Result userGet(Get operation) {
    return connection.get(USERS_TABLE, operation);
  }

  protected void userPut(Put operation) {
    connection.put(USERS_TABLE, operation);
  }

  protected void userDelete(Delete operation) {
    connection.delete(USERS_TABLE, operation);
  }
}
