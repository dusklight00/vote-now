package trainbookingapp.trainbookingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @GetMapping("/")
  public String mainPage() {
    return "main-page";
  }

  @GetMapping("/admin-login")
  public String adminLoginPage() {
    return "admin-login-page";
  }

  @GetMapping("/admin-dashboard")
  public String adminDashboardPage() {
    return "admin-dashboard-page";
  }

  @GetMapping("/admin-create-election")
  public String adminCreateElectionPage() {
    return "admin-create-election";
  }

  @GetMapping("/admin-manage-voters")
  public String adminManageVotersPage() {
    return "admin-manage-voters";
  }

  @GetMapping("/forgot-password")
  public String forgotPasswordPage() {
    return "forgot-password-page";
  }

  @GetMapping("/voter-login")
  public String voterLoginPage() {
    return "voter-login";
  }

  @GetMapping("/voter-register")
  public String voterRegisterPage() {
    return "voter-register";
  }

  @GetMapping("/voter-dashboard")
  public String voterDashboardPage() {
    return "voter-dashboard";
  }

  @GetMapping("/voter-edit-profile")
  public String editProfilePage() {
    return "voter-edit-profile";
  }

  @GetMapping("/voter-settings")
  public String voterSettingsPage() {
    return "voter-settings";
  }

  @GetMapping("/view-results")
  public String viewResultsPage() {
    return "view-results";
  }
}
