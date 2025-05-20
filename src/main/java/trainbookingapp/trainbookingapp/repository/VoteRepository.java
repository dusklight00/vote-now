package trainbookingapp.trainbookingapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import trainbookingapp.trainbookingapp.entity.Candidate;
import trainbookingapp.trainbookingapp.entity.Election;
import trainbookingapp.trainbookingapp.entity.User;
import trainbookingapp.trainbookingapp.entity.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {
  // Check if a user has already voted in an election
  boolean existsByVoterAndElection(User voter, Election election);

  // Count votes for a specific candidate
  long countByCandidate(Candidate candidate);

  // Count votes for all candidates in a specific election
  @Query(
    "SELECT v.candidate.id, COUNT(v) FROM Vote v WHERE v.election = :election GROUP BY v.candidate.id"
  )
  Iterable<Object[]> countVotesByElectionGroupByCandidate(
    @Param("election") Election election
  );
}
