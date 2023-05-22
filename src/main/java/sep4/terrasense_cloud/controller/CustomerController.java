package sep4.terrasense_cloud.controller;

import org.springframework.web.bind.annotation.*;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.service.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register/")
    @ResponseBody
    public boolean register(@RequestBody Customer customer){
        return customerService.register(customer);
    }

    @PostMapping("/login/")
    @ResponseBody
    public boolean login(@RequestBody Customer customer){
        return customerService.login(customer);
    }
}
