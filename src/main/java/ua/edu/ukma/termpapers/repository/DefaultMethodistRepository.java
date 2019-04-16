package ua.edu.ukma.termpapers.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entity.Methodist;

@Repository
public class DefaultMethodistRepository extends DefaultCrudRepository<Methodist> implements
    MethodistRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMethodistRepository.class);

  public DefaultMethodistRepository(HBaseConnection connection) {
    super(Methodist.class, connection);
  }
}
