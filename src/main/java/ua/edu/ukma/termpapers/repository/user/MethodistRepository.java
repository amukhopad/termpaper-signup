package ua.edu.ukma.termpapers.repository.user;

import ua.edu.ukma.termpapers.entities.users.Methodist;

public interface MethodistRepository extends UserRepository<Methodist> {
  byte[] METHODIST_CF = "methodists".getBytes();

  byte[] CATEGORY = "category".getBytes();
}
