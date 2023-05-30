package sep4.terrasense_cloud.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sep4.terrasense_cloud.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    public Optional<Customer> findByUsername(String username);
}
