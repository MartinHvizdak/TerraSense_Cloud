package sep4.terrasense_cloud.service.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.CustomerRepository;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.model.LoginRequest;
import sep4.terrasense_cloud.service.services.CustomerService;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean existsByUsername(String username){
        Optional<Customer> user = customerRepository.findByUsername(username);
        return user.isPresent();
    }
    public void register(Customer customer){
        customerRepository.save(customer);
    }

    public Customer login(LoginRequest customer){

        Optional<Customer> customer1=customerRepository.findByUsername(customer.getUsername());
        if(customer1.isPresent()){
            Customer user=customer1.get();
        if(user.getPassword().equals(customer.getPassword()))
        {
            return user;
        }
        else
        {
            throw new BadCredentialsException("Invalid username or password.");
        }}
        else {
            throw new BadCredentialsException("User does not exist");
        }
    }
}
