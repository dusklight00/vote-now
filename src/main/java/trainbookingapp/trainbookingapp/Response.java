package trainbookingapp.trainbookingapp;

public class Response {

  public String message;
  public int status;
  public Object data;

  public Response() {}

  public Response(String status, String message, Object data) {
    this.status = "success".equals(status) ? 200 : 400; // Convert string status to int
    this.message = message;
    this.data = data;
  }
  
  // Add getters for proper JSON serialization
  public String getMessage() {
    return message;
  }
  
  public int getStatus() {
    return status;
  }
  
  public Object getData() {
    return data;
  }
}
