package ua.edu.ukma.termpapers.repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.repository.util.HBaseUtil;

public abstract class DefaultCrudRepository<T> implements CrudRepository<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCrudRepository.class);

  private final Class<T> type;
  private final HBaseConnection connection;

  public DefaultCrudRepository(Class<T> type, HBaseConnection connection) {
    this.type = type;
    this.connection = connection;
  }

  @Override
  public void put(T entity) {
    Put operation = HBaseUtil.buildPutOperation(entity);

    connection.put(HBaseUtil.getTableNameFor(entity), operation);
  }

  @Override
  public T get(String key) {
    if (StringUtils.isEmpty(key)) {
      return null;
    }

    Get operation = HBaseUtil.buildGetOperation(type, key.getBytes());
    Result result = connection.get(HBaseUtil.getTableNameFor(type), operation);
    if (result.isEmpty()) {
      return null;
    }

    return HBaseUtil.buildFromResult(result, type, connection);
  }

  @Override
  public void delete(String key) {
    Delete operation = HBaseUtil.buildDeleteOperation(key);

    connection.delete(HBaseUtil.getTableNameFor(type), operation);
  }

  @Override
  public List<T> getAll() {
    List<Result> results = connection.scan(HBaseUtil.getTableNameFor(type));
    return results.stream()
        .map(result -> HBaseUtil.buildFromResult(result, type, connection))
        .collect(Collectors.toList());
  }

  @Override
  public List<T> getBySingleFieldValue(String fieldName, String fieldValue) {
    if (fieldName == null || fieldValue == null) {
      return Collections.emptyList();
    }

    Filter filter = new SingleColumnValueFilter(
        HBaseUtil.getColumnFamily(type, fieldName).getBytes(),
        HBaseUtil.getColumnName(type, fieldName).getBytes(),
        CompareOperator.EQUAL,
        fieldValue.getBytes());

    List<Result> results = connection.scan(HBaseUtil.getTableNameFor(type), filter);
    return results.stream()
        .map(result -> HBaseUtil.buildFromResult(result, type, connection))
        .collect(Collectors.toList());
  }
}
