package ua.edu.ukma.termpapers.repository.user.impl;

import static java.lang.String.format;
import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getEnum;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getString;

import java.lang.reflect.InvocationTargetException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entities.enums.Faculty;
import ua.edu.ukma.termpapers.entities.enums.Role;
import ua.edu.ukma.termpapers.entities.users.User;
import ua.edu.ukma.termpapers.repository.user.UserRepository;

abstract public class AbstractUserRepository<U extends User> implements UserRepository<U> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUserRepository.class);

  private HBaseConnection connection;
  private Class<U> type;

  public AbstractUserRepository(HBaseConnection connection, Class<U> type) {
    this.connection = connection;
    this.type = type;
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
    operation.addColumn(COMMON_CF, ROLE, toBytes(user.getRole().name()));
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

  protected U buildFromResult(Result result) {
    if (result.isEmpty()) {
      return null;
    }
    U entity;
    try {
      entity = type.getConstructor().newInstance();
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
      LOGGER.error(format("No default constructor found for type %s", type.getName()), ex);
      return null;
    }
    entity.setEmail(Bytes.toString(result.getRow()))
        .setGivenName(getString(result, COMMON_CF, GIVEN_NAME))
        .setFathersName(getString(result, COMMON_CF, FATHER_NAME))
        .setFamilyName(getString(result, COMMON_CF, FAMILY_NAME))
        .setFaculty(getEnum(Faculty.class, result, COMMON_CF, FACULTY))
        .setDrfo(getString(result, COMMON_CF, DRFO))
        .setRole(getEnum(Role.class, result, COMMON_CF, ROLE));
    return entity;
  }
}
