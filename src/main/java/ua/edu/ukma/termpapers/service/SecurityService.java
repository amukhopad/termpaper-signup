package ua.edu.ukma.termpapers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.edu.ukma.termpapers.repository.UserRepository;

@Service
public class SecurityService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

  private final UserDetailsService userDetailsService;
  private final AuthenticationManager authenticationManager;

  public SecurityService(UserRepository userRepository,
      AuthenticationManager authenticationManager) {
    this.userDetailsService = userRepository;
    this.authenticationManager = authenticationManager;
  }

  public String findLoggedInUsername() {
    Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
    if (details instanceof UserDetails) {
      return ((UserDetails) details).getUsername();
    }
    return null;
  }

  public void autoLogin(String username, String password) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(userDetails, password,
            userDetails.getAuthorities());

    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    if (usernamePasswordAuthenticationToken.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      LOGGER.debug(String.format("Auto login %s successfully!", username));
    }
  }
}
