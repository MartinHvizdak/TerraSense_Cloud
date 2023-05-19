package sep4.terrasense_cloud;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TerraSenseCloudApplicationTests {

  /*  @Test
    void contextLoads() {
    }*/
/*
    @Test
    void testHashing(){
        String hash = "$argon2i$v=19$m=65536,t=10,p=1$VX/tgvp4pd8pkLklGTnthw$VAUgGEDiF5Y4vwcnYSzrk+5Yw/kSFw/YDNeo2107On4";

        Argon2 encoder = Argon2Factory.create();
        int iterations = 10;
        int memory = 65536;
        int parallelism = 1;

        System.out.println(encoder.verify(hash, "password"));
    }

 */
}
