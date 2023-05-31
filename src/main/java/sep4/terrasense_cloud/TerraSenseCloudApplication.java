package sep4.terrasense_cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class TerraSenseCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TerraSenseCloudApplication.class, args);

    }

}
