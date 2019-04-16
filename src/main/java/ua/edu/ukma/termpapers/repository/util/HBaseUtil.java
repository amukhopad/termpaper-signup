package ua.edu.ukma.termpapers.repository.util;

import static java.lang.String.format;
import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import ua.edu.ukma.termpapers.annotation.Column;
import ua.edu.ukma.termpapers.annotation.Id;
import ua.edu.ukma.termpapers.annotation.Table;
import ua.edu.ukma.termpapers.exception.HBasePersistenceException;

public class HBaseUtil {

  public static final String getterPrefix = "get";
  public static final String setterPrefix = "set";

  public static <T extends Enum<T>> T getEnum(Class<T> enumType,
      Result result, byte[] family, byte[] column) {
    String name = Bytes.toString(result.getValue(family, column));
    return (name == null) ? null : Enum.valueOf(enumType, name);
  }

  public static String getString(Result result, byte[] family, byte[] column) {
    return Bytes.toString(result.getValue(family, column));
  }

  public static int getInt(Result result, byte[] family, byte[] column) {
    return Bytes.toInt(result.getValue(family, column));
  }

  public static void ifPresent(Put put, byte[] cf, byte[] column, Integer value) {
    if (value != null) {
      put.addColumn(cf, column, toBytes(value));
    }
  }

  public static void ifPresent(Put put, byte[] cf, byte[] column, String value) {
    if (value != null) {
      put.addColumn(cf, column, toBytes(value));
    }
  }

  public static TableName getTableNameFor(Object entity) {
    Class<?> type = entity.getClass();
    return getTableNameFor(type);
  }

  public static TableName getTableNameFor(Class<?> type) {
    if (!type.isAnnotationPresent(Table.class)) {
      throw new HBasePersistenceException(
          format("@Table annotation is not found for type %s", type.getName()));
    }

    String tableName = type.getAnnotation(Table.class).name();
    if (StringUtils.isEmpty(tableName)) {
      tableName = type.getName().toLowerCase();
    }

    return TableName.valueOf(tableName);
  }

  public static Put buildPutOperation(Object entity) {
    Class<?> type = entity.getClass();
    String rowValue;
    List<Field> dataFields;
    try {
      Field idField = getIdField(type);
      Method idGetter = getGetterFor(idField);
      rowValue = idGetter.invoke(entity).toString();
      dataFields = getDataFields(type);
    } catch (IllegalAccessException | InvocationTargetException ex) {
      throw new HBasePersistenceException(
          format("Error while creating Put operation for type %s", type.getName()), ex);
    }

    Put operation = new Put(rowValue.getBytes());
    dataFields.forEach(field -> {
      String columnFamily = field.getAnnotation(Column.class).family();
      String columnName = field.getAnnotation(Column.class).name();
      Method columnGetter = getGetterFor(field);
      String columnValue;
      try {
        columnValue = columnGetter.invoke(entity).toString();
      } catch (IllegalAccessException | InvocationTargetException ex) {
        throw new HBasePersistenceException(
            format("Error while creating Put operation for type %s", type.getName()), ex);
      }
      operation.addColumn(columnFamily.getBytes(), columnName.getBytes(), columnValue.getBytes());
    });

    return operation;
  }

  public static Get buildGetOperation(Class<?> type, String key) {
    Get operation = new Get(key.getBytes());
    List<Field> dataFields = getDataFields(type);
    dataFields.stream()
        .map(field -> field.getAnnotation(Column.class).family())
        .distinct()
        .forEach(family -> operation.addFamily(family.getBytes()));

    return operation;
  }

  public static Delete buildDeleteOperation(String key) {
    return new Delete(key.getBytes());
  }

  public static Field getIdField(Class<?> type) {
    return Arrays.stream(type.getDeclaredFields())
        .filter(field -> field.isAnnotationPresent(Id.class))
        .findAny()
        .orElseThrow(() -> new HBasePersistenceException(
            format("Field with @Id annotation is not found for type %s", type.getName())));
  }

  public static Method getGetterFor(Field field) {
    String fieldName = field.getName();
    String getterName =
        getterPrefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    try {
      return field.getDeclaringClass().getDeclaredMethod(getterName);
    } catch (NoSuchMethodException ex) {
      throw new HBasePersistenceException(
          format("Getter method is not found for field %s for type %s",
              field.getName(), field.getDeclaringClass().getName()));
    }
  }

  public static Method getSetterFor(Field field) {
    String fieldName = field.getName();
    String setterName =
        setterPrefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    try {
      return field.getDeclaringClass().getDeclaredMethod(setterName, field.getType());
    } catch (NoSuchMethodException ex) {
      throw new HBasePersistenceException(
          format("Setter method is not found for field %s for type %s",
              field.getName(), field.getDeclaringClass().getName()));
    }
  }

  public static List<Field> getDataFields(Class<?> type) {
    return Arrays.stream(type.getDeclaredFields())
        .filter(field -> field.isAnnotationPresent(Column.class))
        .collect(Collectors.toList());
  }
}
