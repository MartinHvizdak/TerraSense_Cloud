package sep4.terrasense_cloud.security;


import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordEncoder {
    public Argon2 getEncoder(){
        return Argon2Factory.create();
    }
}
