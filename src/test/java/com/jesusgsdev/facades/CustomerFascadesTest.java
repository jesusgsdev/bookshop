package com.jesusgsdev.facades;

import com.jesusgsdev.dtos.CustomerDTO;
import com.jesusgsdev.entities.Customer;
import com.jesusgsdev.services.CustomerService;
import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class CustomerFascadesTest {


    @InjectMocks private CustomerFacade customerFacade;

    @Mock private CustomerService customerService;

    @BeforeEach public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Add new customer in the Bookshop")
    public void addCustomerTest() {
        //Given
        String email="johndoe@mailinator.com";
        Customer customer = Customer
                .builder().name("pedro").surname("pajares").address("avenida palmera45")
                .email(email).id(1L).build();


     //   Optional<Customer> customerByEmailExpected = customerService.findCustomerByEmail(email);
        Optional<Customer> customerByEmailExpected = Optional.of(customer);

        given(customerService.findCustomerByEmail(email)).willReturn(customerByEmailExpected);

       //When
        CustomerDTO expectedCustomer  = customerFacade.addCustomer(CustomerDTO.fromCustomer(customer));

        //Then
         CustomerDTO customerFounded = CustomerDTO.fromCustomer(customerByEmailExpected.get());

         assertEquals(expectedCustomer,customerFounded);

    }

    @Test
    @DisplayName("Find customers by email in Bookshop")
    public void findCustomerByEmailTest() { // tampoco me responden los metodos nose porque
        //Given
        String email="johndoe@mailinator.com";
        Customer customer = Customer
                .builder().name("pedro").surname("pajares").address("avenida palmera45")
                .email(email).id(1L).build();
        // Optional<Customer> expectedCustomer = customerService.findCustomerByEmail(email);
        Optional<Customer> expectedCustomer = Optional.of(customer);
       given(customerService.findCustomerByEmail(email)).willReturn(expectedCustomer);

        //When
         CustomerDTO expectedDTO = customerFacade.findCustomerByEmail(email);

        //Then
        assertAll( "Customers found",
                () -> assertThat(Lists.list(expectedDTO), not(IsEmptyCollection.empty())),
                () -> assertThat(Lists.list(expectedDTO), hasSize(1)),
                () -> assertEquals(expectedDTO.getEmail(), email)
        );
    }



}