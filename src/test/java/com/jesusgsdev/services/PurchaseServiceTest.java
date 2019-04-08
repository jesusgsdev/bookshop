package com.jesusgsdev.services;

import com.jesusgsdev.constants.Currency;
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

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;

    @Mock
    private PurchaseRepository purchaseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Add a new find of purchases for customers by email in the Bookshop")
    public void findPurchasesByCustomerEmailTest() {
        //Given
        String email = "johndoe@mailinator.com";
        Customer customer = Customer
                .builder()
                    .name("pedro")
                    .surname("pajares")
                    .address("avenida palmera45")
                    .email("johndoe@mailinator.com")
                    .id(1L)
                .build();
        Customer customer2 = Customer
                .builder()
                    .name("pedro2")
                    .surname("pajares2")
                    .address("avenida palmera42")
                    .email("johndoe@mailinator.com")
                    .id(1L)
                .build();
        Customer customer3 = Customer
                .builder()
                    .name("pedro3")
                    .surname("pajares3")
                    .address("avenida palmera453")
                    .email("johndoe@mailinator.com")
                    .id(1L)
                .build();
        Book book = Book
                .builder()
                    .isbn("ISBN00001")
                    .title("Book Name")
                    .price(9.99)
                    .author("Author Name")
                    .pages(200)
                    .provider("provider")
                .build();

        Purchase purchase = Purchase
                .builder()
                    .customer(customer)
                    .price(1.20)
                    .book(book)
                    .paymentMethod(PaymentMethod.PAYPAL)
                    .currency(Currency.EUR.name())
                .build();
        Purchase purchase2 = Purchase
                .builder()
                    .customer(customer2)
                    .price(1.20)
                    .book(book)
                    .paymentMethod(PaymentMethod.PAYPAL)
                    .currency(Currency.EUR.name())
                    .build();
        Purchase purchase3 = Purchase
                .builder()
                    .customer(customer3)
                    .price(1.20)
                    .book(book)
                    .paymentMethod(PaymentMethod.PAYPAL)
                    .currency(Currency.EUR.name())
                .build();

        given(purchaseRepository.findPurchaseByCustomerEmail(email)).willReturn(Lists.newArrayList(purchase,purchase2,purchase3));

        //When
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
        Book book1 = Book
                .builder()
                .isbn("ISBN00001")
                .title("Book Name")
                .price(9.99)
                .author("Author Name")
                .pages(200)
                .provider("provider")
                .id(id)
                .build();

        Customer customer = Customer
                .builder()
                    .name("pedro")
                    .surname("pajares")
                    .address("avenida palmera45")
                    .email("johndoe@mailinator.com")
                    .id(1L)
                .build();

        Purchase purchase = Purchase
                .builder()
                    .customer(customer)
                    .price(1.20)
                    .book(book1)
                    .paymentMethod(PaymentMethod.PAYPAL)
                    .currency(Currency.EUR.name())
                .build();

        given(purchaseRepository.findPurchaseByBookId(id.toString())).willReturn(Lists.newArrayList(purchase));

        //When
        List<Purchase> purchaseFound = purchaseService.findPurchasesByBookId((String.valueOf(id)));


        //Then
        assertAll("Purchases for this book found",
                () -> assertThat(purchaseFound, not(IsEmptyCollection.empty())),
                () -> assertThat(purchaseFound, hasSize(1)),
                () -> assertTrue(purchaseFound.stream().allMatch(custom -> custom.getBook().getId().equals((id))))
                );
    }
}
