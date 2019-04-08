package com.jesusgsdev.services;

import com.jesusgsdev.entities.Customer;
import com.jesusgsdev.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Add a new customer in the Bookshop")
    public void saveTest() {
        //Given
        Customer customer = Customer
                .builder()
                    .name("pedro")
                    .surname("pajares")
                    .address("avenida palmera45")
                    .email("johndoe@mailinator.com")
                    .id(1L)
                .build();
        given(customerRepository.save(ArgumentMatchers.any(Customer.class))).willReturn(customer);

        //When
        Customer savedCustomer = customerService.save(customer);

        //Then
        assertNotNull(savedCustomer);
        assertEquals(new Long(1), customer.getId());
    }

    @Test
    @DisplayName("Add a new find for email customer in the Bookshop")
    public void findCustomerByEmailTest() {
        //Given
        String email = "johndoe@mailinator.com";
        Customer customer = Customer
                .builder()
                .name("pedro")
                .surname("pajares")
                .address("avenida palmera45")
                .email(email)
                .id(1L)
                .build();
        given(customerRepository.findCustomerByEmail(email)).willReturn(Optional.of(customer));

        //When
        FacadesTest_Customer&purchase
        Optional customerFound = customerService.findCustomerByEmail(email);


        //Then
        assertAll("Customer found",
                () -> assertTrue(customerFound.isPresent()),
                () -> assertEquals(((Customer)customerFound.get()).getEmail(), email)
                );
    }
}
