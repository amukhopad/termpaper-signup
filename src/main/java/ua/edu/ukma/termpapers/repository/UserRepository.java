package ua.edu.ukma.termpapers.repository;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.edu.ukma.termpapers.entity.User;

public interface UserRepository extends CrudRepository<User>, UserDetailsService {

}
