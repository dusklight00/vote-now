package trainbookingapp.trainbookingapp.controllers.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import trainbookingapp.trainbookingapp.Response;
import trainbookingapp.trainbookingapp.entity.Candidate;
import trainbookingapp.trainbookingapp.entity.Election;
import trainbookingapp.trainbookingapp.entity.User;
import trainbookingapp.trainbookingapp.entity.Vote;
import trainbookingapp.trainbookingapp.repository.CandidateRepository;
import trainbookingapp.trainbookingapp.repository.ElectionRepository;
import trainbookingapp.trainbookingapp.repository.UserRepository;
import trainbookingapp.trainbookingapp.repository.VoteRepository;

@RestController
@RequestMapping("/api")
public class VoteController {

  @Autowired
  private VoteRepository voteRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ElectionRepository electionRepository;

  @Autowired
  private CandidateRepository candidateRepository;

  @PostMapping("/cast-vote")
  public Response castVote(
    @RequestParam String userAadhar,
    @RequestParam String password,
    @RequestParam Long electionId,
    @RequestParam Long candidateId
  ) {
    try {
      // Verify user credentials
      User voter = userRepository.findById(userAadhar).orElse(null);
      if (voter == null || !voter.getPassword().equals(password)) {
        return new Response("error", "Invalid credentials", null);
      }

      // Find the election
      Election election = electionRepository.findById(electionId).orElse(null);
      if (election == null) {
        return new Response("error", "Election not found", null);
      }

      // Check if election is active
      if (!election.isActive()) {
        return new Response("error", "This election is not active", null);
      }

      // Check if current time is within election period
      LocalDateTime now = LocalDateTime.now();
      if (
        now.isBefore(election.getStartDateTime()) ||
        now.isAfter(election.getEndDateTime())
      ) {
        return new Response(
          "error",
          "Voting is not currently open for this election",
          null
        );
      }

      // Check if user has already voted in this election
      if (voteRepository.existsByVoterAndElection(voter, election)) {
        return new Response(
          "error",
          "You have already cast your vote in this election",
          null
        );
      }

      // Find the candidate
      Candidate candidate = candidateRepository
        .findById(candidateId)
        .orElse(null);
      if (candidate == null) {
        return new Response("error", "Candidate not found", null);
      }

      // Check if candidate belongs to the election
      if (!candidate.getElection().getId().equals(election.getId())) {
        return new Response(
          "error",
          "Candidate does not belong to this election",
          null
        );
      }

      // Create and save vote
      Vote vote = new Vote(voter, candidate, election, now);
      voteRepository.save(vote);

      // Update user voting status
      voter.setHasVoted(true);
      userRepository.save(voter);

      return new Response("success", "Vote cast successfully", null);
    } catch (Exception e) {
      return new Response(
        "error",
        "Failed to cast vote: " + e.getMessage(),
        null
      );
    }
  }

  @GetMapping("/election-results")
  public Response getElectionResults(@RequestParam Long electionId) {
    try {
      // Find the election
      Election election = electionRepository.findById(electionId).orElse(null);
      if (election == null) {
        return new Response("error", "Election not found", null);
      }

      // Get vote counts by candidate
      Iterable<Object[]> results = voteRepository.countVotesByElectionGroupByCandidate(
        election
      );

      // Get all candidates for this election
      Iterable<Candidate> candidates = candidateRepository.findByElection(
        election
      );

      // Create a map of candidateId to candidate
      Map<Long, Candidate> candidateMap = new HashMap<>();
      for (Candidate candidate : candidates) {
        candidateMap.put(candidate.getId(), candidate);
      }

      // Format results
      List<Map<String, Object>> formattedResults = new ArrayList<>();
      for (Object[] result : results) {
        Long candidateId = (Long) result[0];
        Long voteCount = (Long) result[1];

        Candidate candidate = candidateMap.get(candidateId);
        if (candidate != null) {
          Map<String, Object> candidateResult = new HashMap<>();
          candidateResult.put("candidateId", candidateId);
          candidateResult.put("name", candidate.getName());
          candidateResult.put("party", candidate.getParty());
          candidateResult.put("votes", voteCount);
          formattedResults.add(candidateResult);
        }
      }

      // Add candidates with zero votes
      for (Candidate candidate : candidates) {
        boolean found = false;
        for (Map<String, Object> result : formattedResults) {
          if (result.get("candidateId").equals(candidate.getId())) {
            found = true;
            break;
          }
        }

        if (!found) {
          Map<String, Object> candidateResult = new HashMap<>();
          candidateResult.put("candidateId", candidate.getId());
          candidateResult.put("name", candidate.getName());
          candidateResult.put("party", candidate.getParty());
          candidateResult.put("votes", 0L);
          formattedResults.add(candidateResult);
        }
      }

      return new Response(
        "success",
        "Election results retrieved successfully",
        formattedResults
      );
    } catch (Exception e) {
      return new Response(
        "error",
        "Failed to retrieve election results: " + e.getMessage(),
        null
      );
    }
  }

  @GetMapping("/voter-participation")
  public Response getVoterParticipation(@RequestParam Long electionId) {
    try {
      // Find the election
      Election election = electionRepository.findById(electionId).orElse(null);
      if (election == null) {
        return new Response("error", "Election not found", null);
      }

      // Count total registered voters
      long totalVoters = userRepository.count();

      // Count voted voters in this election by counting unique voter IDs
      Iterable<Vote> votes = voteRepository.findAll();
      int votedCount = 0;
      for (Vote vote : votes) {
        if (vote.getElection().getId().equals(election.getId())) {
          votedCount++;
        }
      }

      // Calculate participation percentage
      double participationPercentage = (totalVoters > 0)
        ? (double) votedCount / totalVoters * 100
        : 0;

      Map<String, Object> result = new HashMap<>();
      result.put("totalVoters", totalVoters);
      result.put("votedCount", votedCount);
      result.put("participationPercentage", participationPercentage);

      return new Response(
        "success",
        "Voter participation retrieved successfully",
        result
      );
    } catch (Exception e) {
      return new Response(
        "error",
        "Failed to retrieve voter participation: " + e.getMessage(),
        null
      );
    }
  }
}
