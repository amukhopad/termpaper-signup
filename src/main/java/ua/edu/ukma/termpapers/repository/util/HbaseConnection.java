package ua.edu.ukma.termpapers.repository.util;

import static org.apache.hadoop.hbase.client.ConnectionFactory.createConnection;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hbase.thirdparty.org.apache.commons.collections4.IteratorUtils;

public class HbaseConnection {
  public static void put(
          Configuration hbaseConf, TableName tableName,
          String rowKey, Function<Put, Put> operation) {
    try (Connection conn = createConnection(hbaseConf);
         Table table = conn.getTable(tableName)
    ) {
      Put put = new Put(Bytes.toBytes(rowKey));
      Put mutated = operation.apply(put);
      table.put(mutated);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public static void delete(Configuration hbaseConf, TableName tableName, String rowKey) {
    try (Connection conn = createConnection(hbaseConf);
         Table table = conn.getTable(tableName)
    ) {
      Delete delete = new Delete(Bytes.toBytes(rowKey));
      table.delete(delete);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public static void delete(
          Configuration hbaseConf, TableName tableName,
          String rowKey, Function<Delete, Delete> operation) {
    try (Connection conn = createConnection(hbaseConf);
         Table table = conn.getTable(tableName)
    ) {
      Delete delete = new Delete(Bytes.toBytes(rowKey));
      Delete mutated = operation.apply(delete);

      table.delete(mutated);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public static Result get(
          Configuration hbaseConf, TableName tableName, String rowKey) {
    try (Connection conn = createConnection(hbaseConf);
         Table table = conn.getTable(tableName)
    ) {
      Get get = new Get(Bytes.toBytes(rowKey));
      return table.get(get);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public static List<Result> scan(
          Configuration hbaseConf, TableName tableName, Filter filters) {
    return scan(hbaseConf, tableName, new FilterList(filters));
  }

  public static List<Result> scan(
          Configuration hbaseConf, TableName tableName, FilterList filters) {
    try (Connection conn = createConnection(hbaseConf);
         Table table = conn.getTable(tableName)
    ) {
      Scan scan = new Scan();
      scan.setFilter(filters);
      ResultScanner scanner = table.getScanner(scan);
      return IteratorUtils.toList(scanner.iterator());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
