package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.termpapers.entities.users.Methodist;
import ua.edu.ukma.termpapers.repository.user.MethodistRepository;
import ua.edu.ukma.termpapers.util.HbaseConnection;

@Repository
public class DefaultMethodistRepository implements MethodistRepository {
  private Configuration hbaseConf;

  public DefaultMethodistRepository(Configuration hbaseConf) {
    this.hbaseConf = hbaseConf;
  }

  @Override
  public void put(Methodist methodist) throws IOException {
    HbaseConnection.put(hbaseConf, USERS_TABLE, () -> {
      Put put = commonData(methodist);
      put.addColumn(METHODIST_CF, CATEGORY, toBytes(methodist.getCategory().name()));

      return put;
    });

  }

  @Override
  public Methodist get(String email) {
    return null;
  }

  @Override
  public void delete(String email) {

  }
}
