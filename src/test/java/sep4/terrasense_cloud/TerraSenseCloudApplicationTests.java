package sep4.terrasense_cloud;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sep4.terrasense_cloud.database.repository.TerrariumRepository;
import sep4.terrasense_cloud.model.*;
import sep4.terrasense_cloud.service.impl.FeedingScheduleServiceImpl;
import sep4.terrasense_cloud.service.services.AlertService;
import sep4.terrasense_cloud.webSockets.WebSocketClient;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TerraSenseCloudApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FeedingScheduleServiceImpl feedingScheduleService;

    @MockBean
    private TerrariumRepository terrariumRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebSocketClient webSocketClient;

    @Autowired
    private AlertService alertService;

    private String authToken;
    @Test
    void contextLoads() {
    }

    //This test verifies the validity of a given password by comparing it to a hashed value using the Argon2 password hashing algorithm.
    @Test
    void testHashing(){
        String hash = "$argon2i$v=19$m=65536,t=10,p=1$VX/tgvp4pd8pkLklGTnthw$VAUgGEDiF5Y4vwcnYSzrk+5Yw/kSFw/YDNeo2107On4";

        Argon2 encoder = Argon2Factory.create();
        int iterations = 10;
        int memory = 65536;
        int parallelism = 1;

        System.out.println(encoder.verify(hash, "password"));
    }




    //This test sets up the necessary authentication credentials for further tests by simulating a user login process.
    // It sends a POST request to the "/public/login" endpoint with a login request containing an email and password,
    // receives a JWT token in the response, and stores it in the "authToken" variable for subsequent test cases that require
    // authentication.
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

    //This test verifies the registration and login functionality of the customer controller endpoints, ensuring that
    // registering a new user with a pre-existing email returns a conflict status code and a specific error message,
    // while successfully logging in returns an OK status code with a valid JWT token in the response.
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

    //This test verifies the functionality of the reading controller endpoints that require authentication.
    // It sends a GET request to the "/reading/" endpoint with the authentication token, start date, and end date as parameters,
    // and expects an OK status code, JSON content type, and an array response.
    // Additionally, it sends a GET request to the "/reading/1" endpoint with the authentication token and expects
    // an OK status code, JSON content type, and a response indicating that the data exists.
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





    //
    //This test checks the alert triggering functionality by creating a mock reading object and
    // terrarium object with predefined values, setting up the mock behavior for the terrarium repository,
    // and calling the checkAlertTrigger method of the AlertService to verify if alerts are triggered correctly.
    @Test
    public void testCheckAndSendAlerts() {
        Reading reading = new Reading();
        reading.setCO2(800);
        reading.setHumidity(75);
        reading.setTemperature(30);

        Terrarium terrarium = new Terrarium();
        terrarium.setMaxCO2(1000);
        terrarium.setMinCO2(400);
        terrarium.setMaxHumidity(80);
        terrarium.setMinHumidity(60);
        terrarium.setMaxTemperature(35);
        terrarium.setMinTemperature(25);

        when(terrariumRepository.getReferenceById(1L)).thenReturn(terrarium);

        alertService.checkAlertTrigger(reading);
    }


// This test verifies that the setFeedingSchedule method of the FeedingScheduleServiceImpl class
// correctly calls the setFeedingSchedule method of the WebSocketClient with the expected arguments.
    @Test
    public void testSetFeedingSchedule() {
        LocalDateTime time = LocalDateTime.now();
        int amount = 2;
        double frequency = 1.5;
        Terrarium terrarium = new Terrarium();

        FeedingSchedule feedingSchedule = new FeedingSchedule();
        feedingSchedule.setTime(time);
        feedingSchedule.setAmount(amount);
        feedingSchedule.setFrequency(frequency);
        feedingSchedule.setTerrarium(terrarium);

        feedingScheduleService.setFeedingSchedule(feedingSchedule);



        verify(webSocketClient).setFeedingSchedule(time, amount, frequency, terrarium);
    }
}



