package sep4.terrasense_cloud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sep4.terrasense_cloud.jwt.JwtTokenUtil;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.model.LoginRequest;
import sep4.terrasense_cloud.model.LoginResponse;
import sep4.terrasense_cloud.model.RegisterRequest;
import sep4.terrasense_cloud.service.services.CustomerService;

@RestController
@RequestMapping("/public")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Customer customer=customerService.login(loginRequest);
            String token = JwtTokenUtil.generateToken(customer.getUsername());

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (customerService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
        }

        Customer newUser = new Customer(registerRequest.getUsername(),"email", registerRequest.getPassword());
        customerService.register(newUser);

        try {
            String token = JwtTokenUtil.generateToken(newUser.getUsername());
            System.out.println("3");
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
