package com.jesusgsdev.services;

import com.jesusgsdev.entities.Book;
import com.jesusgsdev.entities.Customer;
import com.jesusgsdev.entities.Purchase;
import com.jesusgsdev.entities.enums.PaymentMethod;
import com.jesusgsdev.repositories.PurchaseRepository;
import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class PurchaseServiceTest {

    @InjectMocks private PurchaseService purchaseService;

    @Mock private PurchaseRepository purchaseRepository;

    @BeforeEach public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Add a new find of purchases for customers by email in the Bookshop")
    public void findPurchasesByCustomerEmailTest() {
        String email = "johndoe@mailinator.com";
        //Given
        Customer customer = new Customer("pedro", "pajares", "avenida palmera45", email);
        Customer customer2 = new Customer("pedr2o", "pajares2", "avenida palmera452", email);
        Customer customer3 = new Customer("pedr2o3", "pajares3", "avenida palmera453", email);

        Book book = new Book("ISBN00001", "Book Name", 9.99, "Author Name Test", 200, "provider");


        PaymentMethod paymentMethod = PaymentMethod.fromValue("paypal").get();


        Purchase purchase = new Purchase(customer,1.20,book, paymentMethod,"Euros");
        Purchase purchase2 = new Purchase(customer2,1.20,book, paymentMethod,"Euros");
        Purchase purchase3 = new Purchase(customer3,1.20,book, paymentMethod,"Euros");

        given(purchaseRepository.findPurchaseByCustomerEmail(email)).willReturn(Lists.newArrayList(purchase,purchase2,purchase3));

        //when
        List<Purchase> listPurchase = purchaseService.findPurchasesByCustomerEmail(email);

        //Then
        assertAll( "customers than  purchase books found",
                () -> assertThat(listPurchase, not(IsEmptyCollection.empty())),
                () -> assertThat(listPurchase, hasSize(3)),
                () -> assertTrue(listPurchase.stream().allMatch(custom -> purchase.getCustomer().getEmail().equals(custom.getCustomer().getEmail())))
                );

    }
    @Test
    @DisplayName("Add a new find for purchases by id of book in the Bookshop")
    public void findPurchaseByBookIdTest() {
        //Given
        Long id = 1L;
        Book book1 = new Book("ISBN00001", "Book Name", 9.99, "Author Name Test", 200, "provider");
        book1.setId(id);

        Customer customer = new Customer("pedro", "pajares", "avenida palmera45", "johndoe@mailinator.com");

        Optional<PaymentMethod> paymentMethodOptional = PaymentMethod.fromValue("paypal");
        PaymentMethod paymentMethod = paymentMethodOptional.get();
        Purchase purchase = new Purchase(customer,1.20,book1, paymentMethod,"Euros");

        given(purchaseRepository.findPurchaseByBookId(id.toString())).willReturn(Lists.newArrayList(purchase));

        //When
        List<Purchase> purchaseFound = purchaseRepository.findPurchaseByBookId((String.valueOf(id)));

        //then
        assertAll("Purchases for this book found",
                () -> assertThat(purchaseFound, not(IsEmptyCollection.empty())),
                () -> assertThat(purchaseFound, hasSize(1)),
                () -> assertTrue(purchaseFound.stream().allMatch(custom -> custom.getBook().getId().equals((id))))
                        );

    }
}
