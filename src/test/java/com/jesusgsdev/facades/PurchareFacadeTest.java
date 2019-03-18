package com.jesusgsdev.facades;


import com.jesusgsdev.controllers.PurchaseController;
import com.jesusgsdev.dtos.PurchaseRequestDTO;
import com.jesusgsdev.dtos.PurchaseResponseDTO;
import com.jesusgsdev.entities.Book;
import com.jesusgsdev.entities.Customer;
import com.jesusgsdev.entities.Purchase;
import com.jesusgsdev.entities.enums.PaymentMethod;
import com.jesusgsdev.services.BookService;
import com.jesusgsdev.services.CustomerService;
import com.jesusgsdev.services.PurchaseService;
import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.jesusgsdev.constants.Currency.GBP;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

public class PurchareFacadeTest {

    @InjectMocks private PurchaseFacade purchaseFacade;

    @Mock     private PurchaseService purchaseService;

    @Mock    private BookService bookService;

    @Mock  private CustomerService customerService;


    @BeforeEach  public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Perform  purchase in Bookshop")
    public void performPurchaseTest( ) {
        //Given
        Book book = Book.builder().isbn("ISBN00001").title("Book Name").price(9.99).author("Manuel").pages(200).provider("provider").build();
        Customer customer = Customer
                .builder().name("pedro").surname("pajares").address("avenida palmera45")
                .email("johndoe@mailinator.com").id(1L).build();

        Purchase purchase = Purchase
                .builder().id(1L).customer(customer).price(book.getPrice()).book(book)
                .paymentMethod(PaymentMethod.PAYPAL).currency(GBP.toString()).build();

        PurchaseRequestDTO purchaseRequestDto  = new PurchaseRequestDTO(customer.getEmail(),book.getIsbn(),PaymentMethod.PAYPAL.toString());

        given(bookService.findBookByISBN(book.getIsbn())).willReturn(Optional.of(book));
        given(customerService.findCustomerByEmail(customer.getEmail())).willReturn(Optional.of(customer));
        given(purchaseService.save(purchase)).willReturn(purchase);

        //When
        PurchaseResponseDTO purchaseRequestDTO = purchaseFacade.performPurchase(purchaseRequestDto);

        //Then
        assertEquals(purchaseRequestDTO.getBook().getISBN(), book.getIsbn());
        assertEquals(purchaseRequestDTO.getBook().getAuthor(), book.getAuthor());
        assertEquals(purchaseRequestDTO.getBook().getTitle(), book.getTitle());
        assertEquals(purchaseRequestDTO.getBook().getPrice(), book.getPrice());
        assertEquals(purchaseRequestDTO.getBook().getPages(), book.getPages());
        assertEquals(purchaseRequestDTO.getBook().getProvider(), book.getProvider());




    }
        @Test
        @DisplayName("Find purcharsers by email in Bookshop") // Esta bien programado pero aveces no encuentra resultados
       public void findPurchasesByCustomerEmail (){
            //Given
            Book book = Book.builder().isbn("ISBN00001").title("Book Name").price(9.99).author("Manuel").pages(200).provider("provider").build();

            String email = "johndoe@mailinator.com";

            Customer customer = Customer.builder().name("pedro").surname("pajares")
                    .address("avenida palmera45").email(email)
                    .id(1L).build();


            Purchase purchase = Purchase
                    .builder().customer(customer).price(book.getPrice()).book(book).paymentMethod(PaymentMethod.PAYPAL)
                    .currency(GBP.toString()).build();

            Purchase purchase2 = Purchase
                    .builder().customer(customer).price(book.getPrice()).book(book)
                    .paymentMethod(PaymentMethod.PAYPAL).currency(GBP.toString()).build();


            Purchase purchase3 = Purchase
                    .builder().customer(customer).price(book.getPrice()).book(book)
                    .paymentMethod(PaymentMethod.PAYPAL).currency(GBP.toString()).build();

              // List<Purchase> listaPurchaseResponseDTO = purchaseService.findPurchasesByCustomerEmail(email);
                 List<Purchase> listaPurchaseResult =  Lists.list(purchase,purchase2,purchase3);


           given(purchaseService.findPurchasesByCustomerEmail(email)).willReturn(listaPurchaseResult);

            //when
            List<PurchaseResponseDTO> purchaseDTOFound =  purchaseFacade.findPurchasesByCustomerEmail(email);

            //Then
            assertAll( "Customers found",
                    () -> assertThat((purchaseDTOFound), not(IsEmptyCollection.empty())),
                    () -> assertThat((purchaseDTOFound), hasSize(3)),
                   () -> assertTrue(listaPurchaseResult.stream().allMatch(purch ->purch.getCustomer().getEmail().equals(email)))

            );
       }


}
