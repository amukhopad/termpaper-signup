package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getEnum;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getString;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.termpapers.entities.enums.Category;
import ua.edu.ukma.termpapers.entities.enums.Faculty;
import ua.edu.ukma.termpapers.entities.users.Methodist;
import ua.edu.ukma.termpapers.repository.user.MethodistRepository;
import ua.edu.ukma.termpapers.repository.util.HbaseConnection;

@Repository
public class DefaultMethodistRepository
        extends AbstractUserRepository<Methodist>
        implements MethodistRepository {

  @Override
  public void put(Methodist methodist) throws IOException {
    HbaseConnection.put(getHbaseConf(), USERS_TABLE, () -> {
      Put put = commonPut(methodist);
      put.addColumn(METHODIST_CF, CATEGORY, toBytes(methodist.getCategory().name()));

      return put;
    });

  }

  @Override
  public Methodist get(String email) throws IOException {
    Result result = HbaseConnection.get(getHbaseConf(), USERS_TABLE, toBytes(email));
    return buildFromResult(result);
  }

  @Override
  public void delete(String email) throws IOException {
    super.delete(email);
  }

  private Methodist buildFromResult(Result result) {
    return new Methodist()
            .setEmail(Bytes.toString(result.getRow()))
            .setCategory(getEnum(Category.class, result, METHODIST_CF, CATEGORY))
            .setGivenName(getString(result, COMMON_CF, GIVEN_NAME))
            .setFathersName(getString(result, COMMON_CF, FATHER_NAME))
            .setFamilyName(getString(result, COMMON_CF, FAMILY_NAME))
            .setFaculty(getEnum(Faculty.class, result, COMMON_CF, FACULTY))
            .setDrfo(getString(result, COMMON_CF, DRFO));
  }
}
