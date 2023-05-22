package sep4.terrasense_cloud.service.impl;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.CustomerRepository;
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

    @Override
    public boolean register(Customer customer){
        try{
            if(customerRepository.existsById(customer.getEmail())){
                return false;
            }
            else{
                customer.setPassword(encoder.hash(iterations, memory, parallelism, customer.getPassword()));
                customerRepository.save(customer);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return false;
    }

    @Override
    public boolean login(Customer customer){
        try {
            if (customerRepository.existsById(customer.getEmail())){
                Customer findCustomer = customerRepository.findById(customer.getEmail()).get();
                return encoder.verify(findCustomer.getPassword(), customer.getPassword());
            }
            else
                return false;

        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return false;
    }
}
