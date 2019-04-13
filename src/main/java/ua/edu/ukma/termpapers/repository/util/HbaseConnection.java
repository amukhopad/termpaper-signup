package ua.edu.ukma.termpapers.repository.util;

import static org.apache.hadoop.hbase.client.ConnectionFactory.createConnection;

import java.io.IOException;
import java.util.function.Supplier;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;

public class HbaseConnection {
  public static void put(Configuration hbaseConf, TableName tableName,
                         Supplier<Put> operation) throws IOException {
    try (Connection conn = createConnection(hbaseConf);
         Table table = conn.getTable(tableName)
    ) {
      Put p = operation.get();
      table.put(p);
    }
  }

  public static void delete(Configuration hbaseConf, TableName tableName,
                            byte[] rowKey) throws IOException {
    try (Connection conn = createConnection(hbaseConf);
         Table table = conn.getTable(tableName)
    ) {
      Delete d = new Delete(rowKey);
      table.delete(d);
    }
  }

  public static Result get(Configuration hbaseConf, TableName tableName,
                           byte[] rowKey) throws IOException {
    try (Connection conn = createConnection(hbaseConf);
         Table table = conn.getTable(tableName)
    ) {
      Get g = new Get(rowKey);
      return table.get(g);
    }
  }
}
