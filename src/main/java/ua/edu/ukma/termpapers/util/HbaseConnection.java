package ua.edu.ukma.termpapers.util;

import static org.apache.hadoop.hbase.client.ConnectionFactory.createConnection;

import java.io.IOException;
import java.util.function.Supplier;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

public class HbaseConnection {
  public static void put(Configuration hbaseConf, TableName tableName,
                         Supplier<Put> operation) throws IOException {
    try (Connection conn = createConnection(hbaseConf);
         Table table = conn.getTable(tableName)
    ) {
      Put put = operation.get();
      table.put(put);
    }
  }
}
