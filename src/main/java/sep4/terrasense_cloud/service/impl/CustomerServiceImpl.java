package sep4.terrasense_cloud.service.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.CustomerRepository;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.model.LoginRequest;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.security.PasswordEncoder;
import sep4.terrasense_cloud.service.services.CustomerService;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    Argon2 encoder = Argon2Factory.create();
    private int iterations = 10;
    private int memory = 65536;
    private int parallelism = 1;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean existsByUsername(String username) {
        Optional<Customer> user = customerRepository.findByUsername(username);
        return user.isPresent();
    }

    public Customer login(LoginRequest customer) {

        Optional<Customer> customer1 = customerRepository.findByUsername(customer.getUsername());
        if (customer1.isPresent()) {
            Customer user = customer1.get();
            if (encoder.verify(user.getPassword(), customer.getPassword())) {
                return user;
            } else {
                throw new BadCredentialsException("Invalid username or password.");
            }
        } else {
            throw new BadCredentialsException("User does not exist");
        }
    }

    @Override
    public void register(Customer customer) {
        try {

                customer.setPassword(encoder.hash(iterations, memory, parallelism, customer.getPassword()));
                customerRepository.save(customer);
       } catch (Exception e) {
            System.out.println(e.getStackTrace());
            throw new BadCredentialsException("Registration unsuccesfull");
        }
    }
}
