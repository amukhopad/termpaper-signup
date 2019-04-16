package ua.edu.ukma.termpapers.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

  public String findLoggedInUsername() {
    Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
    if (details instanceof UserDetails) {
      return ((UserDetails) details).getUsername();
    }
    return null;
  }
}
