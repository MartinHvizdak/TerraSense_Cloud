package sep4.terrasense_cloud.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "\"customer\"")
public class Customer implements UserDetails {

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

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
        this.username = "test";
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the user's authorities/roles
        // For simplicity, let's assume a single role named "ROLE_USER"
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming the account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming the account is never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming the credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Assuming the account is always enabled
    }
}
