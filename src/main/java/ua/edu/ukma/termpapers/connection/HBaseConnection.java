package ua.edu.ukma.termpapers.connection;

import static java.lang.String.format;
import static org.apache.hadoop.hbase.client.ConnectionFactory.createConnection;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
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
import org.apache.hbase.thirdparty.org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HBaseConnection {

  private static final Logger LOGGER = LoggerFactory.getLogger(HBaseConnection.class);

  private Configuration configuration;

  public HBaseConnection(Configuration configuration) {
    this.configuration = configuration;
  }

  public void put(TableName tableName, Put operation) {
    try (Connection conn = createConnection(configuration);
        Table table = conn.getTable(tableName)) {
      table.put(operation);
    } catch (IOException ex) {
      LOGGER.error(
          format("Error during Put operation with table %s", tableName.getNameAsString()), ex);
    }
  }

  public void delete(TableName tableName, Delete operation) {
    try (Connection conn = createConnection(configuration);
        Table table = conn.getTable(tableName)) {
      table.delete(operation);
    } catch (IOException ex) {
      LOGGER.error(
          format("Error during Delete operation with table %s", tableName.getNameAsString()), ex);
    }
  }

  public Result get(TableName tableName, String rowKey) {
    Get operation = new Get(rowKey.getBytes());

    return get(tableName, operation);
  }

  public Result get(TableName tableName, Get operation) {
    try (Connection conn = createConnection(configuration);
        Table table = conn.getTable(tableName)
    ) {
      return table.get(operation);
    } catch (IOException ex) {
      LOGGER.error(
          format("Error during Get operation with table %s", tableName.getNameAsString()), ex);
      return Result.EMPTY_RESULT;
    }
  }

  public List<Result> scan(TableName tableName, Scan operation) {
    try (Connection conn = createConnection(configuration);
        Table table = conn.getTable(tableName)) {

      ResultScanner scanner = table.getScanner(operation);
      return IteratorUtils.toList(scanner.iterator());
    } catch (IOException ex) {
      LOGGER.error(
          format("Error during Scan operation with table %s", tableName.getNameAsString()), ex);
      return Collections.emptyList();
    }
  }

  public List<Result> scan(TableName tableName, Filter filter) {
    Scan operation = new Scan();
    operation.setFilter(filter);
    return scan(tableName, operation);
  }

  public List<Result> scan(TableName tableName) {
    Scan operation = new Scan();
    return scan(tableName, operation);
  }
}
