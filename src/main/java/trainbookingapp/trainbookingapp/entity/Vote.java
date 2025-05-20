package trainbookingapp.trainbookingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Vote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_aadhar")
  private User voter;

  @ManyToOne
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  @ManyToOne
  @JoinColumn(name = "election_id")
  private Election election;

  private LocalDateTime timestamp;

  // For anonymous voting, we can hash the voter ID or use other mechanisms
  private String voterHash;

  // Default constructor required by JPA
  public Vote() {}

  public Vote(
    User voter,
    Candidate candidate,
    Election election,
    LocalDateTime timestamp
  ) {
    this.voter = voter;
    this.candidate = candidate;
    this.election = election;
    this.timestamp = timestamp;
    // Generate a hash of the voter for anonymity
    if (voter != null) {
      this.voterHash = String.valueOf(voter.getAadhar().hashCode());
    }
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getVoter() {
    return voter;
  }

  public void setVoter(User voter) {
    this.voter = voter;
    if (voter != null) {
      this.voterHash = String.valueOf(voter.getAadhar().hashCode());
    }
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }

  public Election getElection() {
    return election;
  }

  public void setElection(Election election) {
    this.election = election;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getVoterHash() {
    return voterHash;
  }
}
