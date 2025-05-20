package trainbookingapp.trainbookingapp.repository;

import org.springframework.data.repository.CrudRepository;
import trainbookingapp.trainbookingapp.entity.Election;

public interface ElectionRepository extends CrudRepository<Election, Long> {
  // Find all active elections
  Iterable<Election> findByActiveTrue();
}
