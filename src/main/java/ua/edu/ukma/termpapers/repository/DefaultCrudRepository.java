package ua.edu.ukma.termpapers.repository;

import static java.lang.String.format;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.ukma.termpapers.annotation.Column;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.exception.HBasePersistenceException;
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

    Get operation = HBaseUtil.buildGetOperation(type, key);
    Result result = connection.get(HBaseUtil.getTableNameFor(type), operation);
    if (result.isEmpty()) {
      return null;
    }

    return buildFromResult(result);
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
        .map(this::buildFromResult)
        .collect(Collectors.toList());
  }

  public T buildFromResult(Result result) {
    T entity;
    try {
      entity = type.getConstructor().newInstance();
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
      throw new HBasePersistenceException(
          format("No default constructor found for type %s", type.getName()), ex);
    }
    Field idField = HBaseUtil.getIdField(type);
    Method idSetter = HBaseUtil.getSetterFor(idField);
    Object idValue = getValueFor(idField, result.getRow());
    try {
      idSetter.invoke(entity, idValue);
    } catch (IllegalAccessException | InvocationTargetException ex) {
      throw new HBasePersistenceException(
          format("No setter found for field %s for type %s", idField.getName(), type.getName()),
          ex);
    }
    List<Field> dataFields = HBaseUtil.getDataFields(type);
    dataFields
        .forEach(field -> {
          Method setter = HBaseUtil.getSetterFor(field);
          String family = field.getAnnotation(Column.class).family();
          String name = field.getAnnotation(Column.class).name();
          Object value = getValueFor(field, result.getValue(family.getBytes(), name.getBytes()));
          try {
            setter.invoke(entity, value);
          } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new HBasePersistenceException(
                format("No setter found for field %s for type %s",
                    field.getName(), type.getName()), ex);
          }
        });

    return entity;
  }

  @SuppressWarnings("unchecked")
  private Object getValueFor(Field field, byte[] result) {
    if (result == null) {
      return null;
    }
    String fieldType = field.getType().getName();

    switch (fieldType) {
      case "java.lang.String":
        return Bytes.toString(result);

      default:
        if (field.getType().isEnum()) {
          return Enum.valueOf((Class<? extends Enum>) field.getType(), Bytes.toString(result));
        }
        throw new HBasePersistenceException(format("Type %s is not supported", fieldType));
    }
  }
}
