package sep4.terrasense_cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sep4.terrasense_cloud.webSockets.WebSocketClient;

@SpringBootApplication
public class TerraSenseCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TerraSenseCloudApplication.class, args);

    }

}
