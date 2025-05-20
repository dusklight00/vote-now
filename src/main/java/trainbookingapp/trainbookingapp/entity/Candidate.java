package trainbookingapp.trainbookingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Candidate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String party;
  private String position;
  private String profilePicture;
  private String manifesto;

  @ManyToOne
  @JoinColumn(name = "election_id")
  private Election election;

  // Default constructor required by JPA
  public Candidate() {}

  public Candidate(
    String name,
    String party,
    String position,
    String profilePicture,
    String manifesto,
    Election election
  ) {
    this.name = name;
    this.party = party;
    this.position = position;
    this.profilePicture = profilePicture;
    this.manifesto = manifesto;
    this.election = election;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getParty() {
    return party;
  }

  public void setParty(String party) {
    this.party = party;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  public String getManifesto() {
    return manifesto;
  }

  public void setManifesto(String manifesto) {
    this.manifesto = manifesto;
  }

  public Election getElection() {
    return election;
  }

  public void setElection(Election election) {
    this.election = election;
  }
}
