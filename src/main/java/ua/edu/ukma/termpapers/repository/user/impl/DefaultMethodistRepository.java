package ua.edu.ukma.termpapers.repository.user.impl;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getEnum;
import static ua.edu.ukma.termpapers.repository.util.HbaseUtil.getString;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entities.enums.Category;
import ua.edu.ukma.termpapers.entities.enums.Faculty;
import ua.edu.ukma.termpapers.entities.users.Methodist;
import ua.edu.ukma.termpapers.repository.user.MethodistRepository;

@Repository
public class DefaultMethodistRepository
    extends AbstractUserRepository<Methodist>
    implements MethodistRepository {

  public DefaultMethodistRepository(HBaseConnection connection) {
    super(connection);
  }

  @Override
  public void put(Methodist methodist) {
    Put operation = buildUserPut(methodist);
    operation.addColumn(METHODIST_CF, CATEGORY, toBytes(methodist.getCategory().name()));

    userPut(operation);
  }

  @Override
  public Methodist get(String email) {
    Get operation = new Get(email.getBytes());
    operation.addFamily(COMMON_CF);
    operation.addFamily(METHODIST_CF);

    Result result = userGet(operation);

    return buildFromResult(result);
  }

  @Override
  public void delete(String email) {
    super.delete(email);
  }

  private Methodist buildFromResult(Result result) {
    return (result.isEmpty()) ? null : new Methodist()
        .setEmail(Bytes.toString(result.getRow()))
        .setCategory(getEnum(Category.class, result, METHODIST_CF, CATEGORY))
        .setGivenName(getString(result, COMMON_CF, GIVEN_NAME))
        .setFathersName(getString(result, COMMON_CF, FATHER_NAME))
        .setFamilyName(getString(result, COMMON_CF, FAMILY_NAME))
        .setFaculty(getEnum(Faculty.class, result, COMMON_CF, FACULTY))
        .setDrfo(getString(result, COMMON_CF, DRFO));
  }
}
