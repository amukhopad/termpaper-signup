package ua.edu.ukma.termpapers.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entity.Student;

@Repository
public class DefaultStudentRepository extends DefaultCrudRepository<Student>
    implements StudentRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultStudentRepository.class);

  public DefaultStudentRepository(HBaseConnection connection) {
    super(Student.class, connection);
  }
}
