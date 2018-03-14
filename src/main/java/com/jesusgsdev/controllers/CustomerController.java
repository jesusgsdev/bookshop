package com.jesusgsdev.controllers;

import com.jesusgsdev.dtos.CustomerDTO;
import com.jesusgsdev.facades.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerFacade customerFacade;

    @PostMapping
    public CustomerDTO addNewCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return customerFacade.addCustomer(customerDTO);
    }

    @GetMapping
    public CustomerDTO findCustomerByEmail(@RequestParam("email") String email) {
        return customerFacade.findCustomerByEmail(email);
    }

}
