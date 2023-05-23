package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.model.LoginRequest;

@Service
public interface CustomerService {
    public boolean existsByUsername(String username);
    public void register(Customer customer);
    public Customer login(LoginRequest customer);
}
