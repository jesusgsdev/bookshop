package com.jesusgsdev.controllers;

import com.jesusgsdev.dtos.BookDTO;
import com.jesusgsdev.dtos.CustomerDTO;
import com.jesusgsdev.entities.Customer;
import com.jesusgsdev.facades.BookFacade;
import com.jesusgsdev.facades.CustomerFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class CustomerControllerTest {



    @InjectMocks private CustomerController customerController;

    @Mock  private CustomerFacade customerFacade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Add customers in Bookshop controllers")
    public void addNewCustomerTest( ){
        Customer customer = Customer
                .builder()
                .name("pedro")
                .surname("pajares")
                .address("avenida palmera45")
                .email("johndoe@mailinator.com")
                .id(1L)
                .build();
        CustomerDTO customerDTO = CustomerDTO.fromCustomer(customer);

        //Given
        given(customerFacade.addCustomer(CustomerDTO.fromCustomer(customer))).willReturn(customerDTO);

        //When
        CustomerDTO  expectedCustomerDTO =  customerController.addNewCustomer(CustomerDTO.fromCustomer(customer));

        //Then
       // assertEquals(expectedCustomerDTO , customerDTO1);

    }


    @Test
    @DisplayName("Find customer by email in Bookshop Controller")
    public void findCustomerByEmailTest(){
        String email="johndoe@mailinator.com";
        Customer customer = Customer.builder().name("pedro").surname("pajares").address("avenida palmera45")
                .email(email).id(1L).build();

        CustomerDTO customerDTO1 =  CustomerDTO.fromCustomer(customer);

        //Given
       given(customerFacade.findCustomerByEmail(email)).willReturn(customerDTO1);

        //When
        CustomerDTO expectedCustomerDTO= customerController.findCustomerByEmail(email);

        //Then
        assertEquals(expectedCustomerDTO, customerDTO1);

    }

}
