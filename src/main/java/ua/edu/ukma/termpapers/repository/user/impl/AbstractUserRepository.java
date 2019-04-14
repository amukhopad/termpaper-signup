package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import java.util.function.Function;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.beans.factory.annotation.Autowired;

import ua.edu.ukma.termpapers.entities.users.User;
import ua.edu.ukma.termpapers.repository.user.UserRepository;
import ua.edu.ukma.termpapers.repository.util.HbaseConnection;

abstract public class AbstractUserRepository<U extends User> implements UserRepository<U> {
  @Autowired
  private Configuration hbaseConf;

  @Override
  public void delete(String email) {
    userDelete(email, p -> p);
  }

  protected Put commonPut(Put put, U user) {
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

  protected Result userGet(String email, Function<Get, Get> operation) {
    return HbaseConnection.get(getHbaseConf(), USERS_TABLE, email, operation);
  }

  protected void userPut(String email, Function<Put, Put> operation) {
    HbaseConnection.put(getHbaseConf(), USERS_TABLE, email, operation);
  }

  protected void userDelete(String email, Function<Delete, Delete> operation) {
    HbaseConnection.delete(getHbaseConf(), USERS_TABLE, email, operation);
  }
}
