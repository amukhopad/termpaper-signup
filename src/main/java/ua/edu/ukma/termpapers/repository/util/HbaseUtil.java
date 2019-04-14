package ua.edu.ukma.termpapers.repository.util;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseUtil {

  public static <T extends Enum<T>> T getEnum(Class<T> enumType,
                                              Result result, byte[] family, byte[] column) {
    return Enum.valueOf(enumType, Bytes.toString(result.getValue(family, column)));
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
}
