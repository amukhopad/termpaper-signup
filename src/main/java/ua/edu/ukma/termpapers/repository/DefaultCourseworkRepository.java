package ua.edu.ukma.termpapers.repository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entity.Coursework;

@Repository
public class DefaultCourseworkRepository
    extends DefaultCrudRepository<Coursework>
    implements CourseworkRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCourseworkRepository.class);

  public DefaultCourseworkRepository(HBaseConnection connection) {
    super(Coursework.class, connection);
  }

  @Override
  public List<Coursework> getFree() {
    return getBySingleFieldValue("student", "");
  }

  @Override
  public List<Coursework> getByStudentId(String id) {
    return getBySingleFieldValue("student", id);
  }

  @Override
  public List<Coursework> getByTeacherId(String id) {
    return getBySingleFieldValue("teacher", id);
  }
}
