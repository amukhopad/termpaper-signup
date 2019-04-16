package ua.edu.ukma.termpapers.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entity.Teacher;

@Repository
public class DefaultTeacherRepository extends DefaultCrudRepository<Teacher>
    implements TeacherRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTeacherRepository.class);

  public DefaultTeacherRepository(HBaseConnection connection) {
    super(Teacher.class, connection);
  }
}
