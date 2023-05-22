package sep4.terrasense_cloud.service.services;

import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.model.Customer;

@Service
public interface CustomerService {
    public boolean register(Customer customer);

    boolean login(Customer customer);
}
