package ua.edu.ukma.termpapers.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.termpapers.connection.HBaseConnection;
import ua.edu.ukma.termpapers.entity.User;

@Repository
public class DefaultUserRepository extends DefaultCrudRepository<User> implements UserRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserRepository.class);

  public DefaultUserRepository(HBaseConnection connection) {
    super(User.class, connection);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = get(username);

    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().name()));
  }
}
