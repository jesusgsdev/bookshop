package com.jesusgsdev.facades;

import com.jesusgsdev.dtos.CustomerDTO;
import com.jesusgsdev.entities.Customer;
import com.jesusgsdev.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jesusgsdev.constants.ErrorCode.CUSTOMER_ALREADY_EXISTS;
import static com.jesusgsdev.constants.ErrorCode.CUSTOMER_NOT_FOUND;

@Service
public class CustomerFacade {

    @Autowired
    private CustomerService customerService;

    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerService.findCustomerByEmail(customerDTO.getEmail());
        if(customerOptional.isPresent()) {
            customerDTO.setErrorMessage(CUSTOMER_ALREADY_EXISTS.getMessage());
            customerDTO.setError(CUSTOMER_ALREADY_EXISTS.getCode());
        } else {
            Customer customer = new Customer(customerDTO.getName(), customerDTO.getSurname(), customerDTO.getAddress(), customerDTO.getEmail());
            customerService.save(customer);
            customerDTO.setId(customer.getId());
        }
        return customerDTO;
    }
    
    public CustomerDTO findCustomerByEmail(String email){
        Optional<Customer> customerOptional = customerService.findCustomerByEmail(email);
        CustomerDTO customerDTO = new CustomerDTO();
        if(!customerOptional.isPresent()) {
            customerDTO.setErrorMessage(CUSTOMER_NOT_FOUND.getMessage());
            customerDTO.setError(CUSTOMER_NOT_FOUND.getCode());
        } else {
            Customer customer = customerOptional.get();
            customerDTO = CustomerDTO.fromCustomer(customer);
        }
        return customerDTO;
    }
    
}
