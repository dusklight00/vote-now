package trainbookingapp.trainbookingapp.controllers.api;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import trainbookingapp.trainbookingapp.Response;
import trainbookingapp.trainbookingapp.entity.Election;
import trainbookingapp.trainbookingapp.repository.ElectionRepository;

@RestController
@RequestMapping("/api")
public class ElectionController {

  @Autowired
  private ElectionRepository electionRepository;

  @GetMapping("/elections")
  public Response getAllElections() {
    try {
      Iterable<Election> elections = electionRepository.findAll();
      return new Response(
        "success",
        "Elections retrieved successfully",
        elections
      );
    } catch (Exception e) {
      return new Response(
        "error",
        "Failed to retrieve elections: " + e.getMessage(),
        null
      );
    }
  }

  @GetMapping("/active-elections")
  public Response getActiveElections() {
    try {
      Iterable<Election> activeElections = electionRepository.findByActiveTrue();
      return new Response(
        "success",
        "Active elections retrieved successfully",
        activeElections
      );
    } catch (Exception e) {
      return new Response(
        "error",
        "Failed to retrieve active elections: " + e.getMessage(),
        null
      );
    }
  }

  @PostMapping("/create-election")
  public Response createElection(
    @RequestParam String title,
    @RequestParam String description,
    @RequestParam String startDateTime,
    @RequestParam String endDateTime
  ) {
    try {
      // Parse date time strings
      LocalDateTime start = LocalDateTime.parse(startDateTime);
      LocalDateTime end = LocalDateTime.parse(endDateTime);

      // Validate dates
      if (start.isAfter(end)) {
        return new Response(
          "error",
          "Start date must be before end date",
          null
        );
      }

      // Create new election
      Election election = new Election();
      election.setTitle(title);
      election.setDescription(description);
      election.setStartDateTime(start);
      election.setEndDateTime(end);
      election.setActive(true);

      electionRepository.save(election);

      return new Response("success", "Election created successfully", election);
    } catch (Exception e) {
      return new Response(
        "error",
        "Failed to create election: " + e.getMessage(),
        null
      );
    }
  }

  @PostMapping("/toggle-election")
  public Response toggleElection(@RequestParam Long electionId) {
    try {
      // Find the election
      Election election = electionRepository.findById(electionId).orElse(null);

      if (election == null) {
        return new Response("error", "Election not found", null);
      }

      // Toggle active status
      election.setActive(!election.isActive());
      electionRepository.save(election);

      String status = election.isActive() ? "activated" : "deactivated";
      return new Response(
        "success",
        "Election " + status + " successfully",
        election
      );
    } catch (Exception e) {
      return new Response(
        "error",
        "Failed to toggle election: " + e.getMessage(),
        null
      );
    }
  }
}
