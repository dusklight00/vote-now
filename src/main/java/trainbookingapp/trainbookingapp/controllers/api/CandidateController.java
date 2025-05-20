package trainbookingapp.trainbookingapp.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import trainbookingapp.trainbookingapp.Response;
import trainbookingapp.trainbookingapp.entity.Candidate;
import trainbookingapp.trainbookingapp.entity.Election;
import trainbookingapp.trainbookingapp.repository.CandidateRepository;
import trainbookingapp.trainbookingapp.repository.ElectionRepository;

@RestController
@RequestMapping("/api")
public class CandidateController {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private ElectionRepository electionRepository;

  @GetMapping("/candidates")
  public Response getCandidatesByElection(@RequestParam Long electionId) {
    try {
      Election election = electionRepository.findById(electionId).orElse(null);
      if (election == null) {
        return new Response("error", "Election not found", null);
      }

      Iterable<Candidate> candidates = candidateRepository.findByElection(
        election
      );
      return new Response(
        "success",
        "Candidates retrieved successfully",
        candidates
      );
    } catch (Exception e) {
      return new Response(
        "error",
        "Failed to retrieve candidates: " + e.getMessage(),
        null
      );
    }
  }  @PostMapping("/add-candidate")
  public Response addCandidate(
    @RequestParam Long electionId,
    @RequestParam String name,
    @RequestParam String party,
    @RequestParam String position,
    @RequestParam(required = false) String profilePicture,
    @RequestParam(required = false) String manifesto
  ) {
    try {
      System.out.println("Adding candidate '" + name + "' to election ID: " + electionId);
      
      // Find the election
      Election election = electionRepository.findById(electionId).orElse(null);

      if (election == null) {
        System.out.println("Error: Election not found with ID: " + electionId);
        return new Response("error", "Election not found", null);
      }

      // Create new candidate
      Candidate candidate = new Candidate();
      candidate.setName(name);
      candidate.setParty(party);
      candidate.setPosition(position);
      candidate.setProfilePicture(profilePicture != null ? profilePicture : "");
      candidate.setManifesto(manifesto != null ? manifesto : "");
      candidate.setElection(election);

      Candidate savedCandidate = candidateRepository.save(candidate);
      System.out.println("Candidate saved successfully: " + savedCandidate.getId() + " - " + savedCandidate.getName() + " for election: " + electionId);

      Response successResponse = new Response("success", "Candidate added successfully", candidate);
      System.out.println("Returning candidate response: " + successResponse.getStatus() + " - " + successResponse.getMessage());
      return successResponse;
    } catch (Exception e) {
      System.out.println("Error adding candidate: " + e.getMessage());
      e.printStackTrace();
      return new Response(
        "error",
        "Failed to add candidate: " + e.getMessage(),
        null
      );
    }
  }

  @PostMapping("/remove-candidate")
  public Response removeCandidate(@RequestParam Long candidateId) {
    try {
      // Check if candidate exists
      if (!candidateRepository.existsById(candidateId)) {
        return new Response("error", "Candidate not found", null);
      }

      // Remove candidate
      candidateRepository.deleteById(candidateId);

      return new Response("success", "Candidate removed successfully", null);
    } catch (Exception e) {
      return new Response(
        "error",
        "Failed to remove candidate: " + e.getMessage(),
        null
      );
    }
  }
}
