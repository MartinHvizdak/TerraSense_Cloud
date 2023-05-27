package sep4.terrasense_cloud;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sep4.terrasense_cloud.model.LoginRequest;
import sep4.terrasense_cloud.model.LoginResponse;
import sep4.terrasense_cloud.model.RegisterRequest;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TerraSenseCloudApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String authToken;
   @Test
    void contextLoads() {
    }

    @Test
    void testHashing(){
        String hash = "$argon2i$v=19$m=65536,t=10,p=1$VX/tgvp4pd8pkLklGTnthw$VAUgGEDiF5Y4vwcnYSzrk+5Yw/kSFw/YDNeo2107On4";

        Argon2 encoder = Argon2Factory.create();
        int iterations = 10;
        int memory = 65536;
        int parallelism = 1;

        System.out.println(encoder.verify(hash, "password"));
    }




    @BeforeEach
    public void setup() throws Exception {
        // Login with a user and obtain the JWT token
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("example@email.com");
        loginRequest.setPassword("asdf1234");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/public/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        LoginResponse loginResponse = objectMapper.readValue(responseContent, LoginResponse.class);
        authToken = loginResponse.getToken();
    }

    @Test
    public void testCustomerControllerEndpoints() throws Exception {
        // Test register endpoint
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("newuser@example.com");
        registerRequest.setUsername("newuser");
        registerRequest.setPassword("password123");
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setEmail("newuser@example.com");
        loginRequest.setPassword("password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/public/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Email is already taken"));

        mockMvc.perform(MockMvcRequestBuilders.post("/public/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());

    }

    @Test
    @WithMockUser
    public void testReadingControllerEndpoints() throws Exception {
        // Test endpoints that require authentication
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();

        mockMvc.perform(MockMvcRequestBuilders.get("/reading/")
                        .header("Authorization", "Bearer " + authToken)
                        .param("start", startDate.toString())
                        .param("end", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        mockMvc.perform(MockMvcRequestBuilders.get("/reading/1")
                        .header("Authorization", "Bearer " + authToken)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists());
    }
}

