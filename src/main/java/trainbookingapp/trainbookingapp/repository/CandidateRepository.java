package trainbookingapp.trainbookingapp.repository;

import org.springframework.data.repository.CrudRepository;
import trainbookingapp.trainbookingapp.entity.Candidate;
import trainbookingapp.trainbookingapp.entity.Election;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {
  // Find all candidates for a specific election
  Iterable<Candidate> findByElection(Election election);
}
