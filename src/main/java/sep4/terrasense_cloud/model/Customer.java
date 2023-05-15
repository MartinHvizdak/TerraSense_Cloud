package sep4.terrasense_cloud.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "\"customer\"")
public class Customer {

    @Id
    private String email;

    private String username;

    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Terrarium> terrariums;

    public Customer() {
    }

    public Customer(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
