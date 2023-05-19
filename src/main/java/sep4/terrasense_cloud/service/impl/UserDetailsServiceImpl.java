package sep4.terrasense_cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sep4.terrasense_cloud.database.repository.CustomerRepository;
import sep4.terrasense_cloud.model.Customer;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(CustomerRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user;
    }
}