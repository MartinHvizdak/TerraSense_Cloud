package sep4.terrasense_cloud.model;

public class LoginRequest {
    private String email;
    private String password;

    // Default constructor (required for JSON deserialization)
    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
