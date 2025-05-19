package trainbookingapp.trainbookingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @GetMapping("/")
  public String mainPage() {
    return "main-page.html";
  }

  @GetMapping("/admin-login")
  public String adminLoginPage() {
    return "admin-login-page.html";
  }

  @GetMapping("/forgot-password")
  public String forgotPasswordPage() {
    return "forgot-password-page.html";
  }

  @GetMapping("/voter-login")
  public String voterLoginPage() {
    return "voter-login.html";
  }

  @GetMapping("/voter-register")
  public String voterRegisterPage() {
    return "voter-register.html";
  }

  @GetMapping("/voter-dashboard")
  public String voterDashboardPage() {
    return "voter-dashboard.html";
  }

  @GetMapping("/edit-profile")
  public String editProfilePage() {
    return "voter-edit-profile.html";
  }
  @GetMapping("/settings")
  public String settingsPage() {
    return "voter-settings.html";
  }

  @GetMapping("/admin-dashboard")
  public String adminElectionManagementPage() {
    return "admin-dashboard-page.html";
  }

  @GetMapping("/create-election")
  public String createElectionPage() {
    return "admin-create-election.html";
  }

  @GetMapping("/manage-voters")
  public String manageVotersPage() {
    return "admin-manage-voters.html";
  }

  @GetMapping("/view-results")
  public String viewResultsPage() {
    return "view-results.html";
  }
}
