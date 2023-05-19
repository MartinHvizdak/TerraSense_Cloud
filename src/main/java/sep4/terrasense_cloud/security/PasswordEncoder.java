package sep4.terrasense_cloud.security;


import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordEncoder {

    private int salt = 10;
    private int hashLength = 65536;

    public Argon2 PasswordEncoder(){
        return Argon2Factory.create(salt, hashLength);
    }
}
