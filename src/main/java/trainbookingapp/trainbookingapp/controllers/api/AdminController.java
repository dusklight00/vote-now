package trainbookingapp.trainbookingapp.controllers.api;

import java.lang.Iterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import trainbookingapp.trainbookingapp.Response;
import trainbookingapp.trainbookingapp.entity.Admin;
import trainbookingapp.trainbookingapp.repository.AdminRepository;

@RestController
@RequestMapping("/api")
public class AdminController {

  @Autowired
  private AdminRepository adminRepository;

  @GetMapping(path = "/admin-login")
  public Response login(
    @RequestParam String username,
    @RequestParam String password
  ) {
    System.out.println(username + " " + password);

    Iterable<Admin> iterator = adminRepository.findAll();

    for (Admin admin : iterator) {
      if (
        admin.getUsername().equals(username) &&
        admin.getPassword().equals(password)
      ) {
        System.out.println(admin.getUsername() + " " + admin.getPassword());
        Response response = new Response();
        response.message = admin.getId().toString();
        response.status = 200;
        return response;
      }
    }
    Response response = new Response();
    response.message = "Username or Password is incorrect";
    response.status = 400;
    return response;
  }

  // QUERY
  // http://localhost:8080/api/register?username=admin&firstName=Admin&lastName=User&email=admin@example.com&password=password
  @GetMapping(path = "/admin-register")
  public Response login(@ModelAttribute Admin admin) {
    adminRepository.save(admin);
    Response response = new Response();
    response.message = "Registration Successful";
    response.status = 200;
    return response;
  }

  @GetMapping(path = "/get-admin")
  public Admin getUser(@RequestParam Long id) {
    Admin admin = adminRepository.findById(id).orElse(null);
    return admin;
  }

  @GetMapping("/change-admin-password")
  public Response changePassword(
    @RequestParam String email,
    @RequestParam String password
  ) {
    Admin admin = adminRepository.findByEmail(email);
    admin.setPassword(password);
    adminRepository.save(admin);
    Response response = new Response();
    response.message = "Password Changed Successfully";
    response.status = 200;
    return response;
  }

  @GetMapping("/get-admin-by-username")
  public Admin getAdminByUsername(@RequestParam String username) {
    Admin admin = adminRepository.findByUsername(username);
    return admin;
  }
}
