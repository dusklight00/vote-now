package trainbookingapp.trainbookingapp;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import org.springframework.boot.context.event.ApplicationReadyEvent;
// import org.springframework.context.event.EventListener;

@SpringBootApplication
public class VotingSystemApplication {

  //   @Autowired
  //   private EmailSenderService SenderService;
  public static void main(String[] args) {
    SpringApplication.run(VotingSystemApplication.class, args);
  }
  //   @EventListener(ApplicationReadyEvent.class)
  //   public void sendEmail() {
  //     SenderService.sendEmail(
  //       "r.rahul.developer@gmail.com",
  //       "Sample Mail ",
  //       "This is a sample email Say hello"
  //     );
  //   }
}
