package trainbookingapp.trainbookingapp.repository;

import org.springframework.data.repository.CrudRepository;
import trainbookingapp.trainbookingapp.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {
  public Admin findByUsername(String username);

  public Admin findByEmail(String email);
}
