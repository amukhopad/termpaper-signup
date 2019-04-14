package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.springframework.beans.factory.annotation.Autowired;

import ua.edu.ukma.termpapers.entities.users.User;
import ua.edu.ukma.termpapers.repository.user.UserRepository;
import ua.edu.ukma.termpapers.repository.util.HbaseConnection;

abstract public class AbstractUserRepository<U extends User> implements UserRepository<U> {
  @Autowired
  private Configuration hbaseConf;

  @Override
  public void delete(String email) throws IOException {
    HbaseConnection.delete(hbaseConf, USERS_TABLE, toBytes(email));
  }

  protected Put commonPut(U user) {
    Put put = new Put(toBytes(user.getEmail()));
    put.addColumn(COMMON_CF, GIVEN_NAME, toBytes(user.getGivenName()));
    put.addColumn(COMMON_CF, FAMILY_NAME, toBytes(user.getFamilyName()));
    put.addColumn(COMMON_CF, FATHER_NAME, toBytes(user.getFathersName()));
    put.addColumn(COMMON_CF, FACULTY, toBytes(user.getFaculty().name()));
    put.addColumn(COMMON_CF, DRFO, toBytes(user.getDrfo()));
    return put;
  }

  protected Configuration getHbaseConf() {
    return hbaseConf;
  }
}
