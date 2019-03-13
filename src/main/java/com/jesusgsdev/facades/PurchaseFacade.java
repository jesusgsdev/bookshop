package com.jesusgsdev.facades;

import com.jesusgsdev.dtos.PurchaseRequestDTO;
import com.jesusgsdev.dtos.PurchaseResponseDTO;
import com.jesusgsdev.entities.Book;
import com.jesusgsdev.entities.Customer;
import com.jesusgsdev.entities.Purchase;
import com.jesusgsdev.entities.enums.PaymentMethod;
import com.jesusgsdev.services.BookService;
import com.jesusgsdev.services.CustomerService;
import com.jesusgsdev.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.jesusgsdev.constants.Currency.GBP;
import static com.jesusgsdev.constants.ErrorCode.*;

@Service
public class PurchaseFacade {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    public PurchaseResponseDTO performPurchase(PurchaseRequestDTO purchaseRequestDTO) {
        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        Optional<Book> bookOptional = bookService.findBookByISBN(purchaseRequestDTO.getBookISBN());
        if(!bookOptional.isPresent()) {
            purchaseResponseDTO.setErrorMessage(BOOK_NOT_FOUND.getMessage());
            purchaseResponseDTO.setError(BOOK_NOT_FOUND.getCode());
            return  purchaseResponseDTO;
        }

        Optional<Customer> customerOptional = customerService.findCustomerByEmail(purchaseRequestDTO.getCustomerEmail());
        if(!customerOptional.isPresent()) {
            purchaseResponseDTO.setErrorMessage(CUSTOMER_NOT_FOUND.getMessage());
            purchaseResponseDTO.setError(CUSTOMER_NOT_FOUND.getCode());
            return  purchaseResponseDTO;
        }

        Optional<PaymentMethod> paymentMethodOptional = PaymentMethod.fromValue(purchaseRequestDTO.getPaymentMethod());
        if(!paymentMethodOptional.isPresent()) {
            purchaseResponseDTO.setErrorMessage(PAYMENT_METHOD_NOT_VALID.getMessage());
            purchaseResponseDTO.setError(PAYMENT_METHOD_NOT_VALID.getCode());
            return purchaseResponseDTO;
        }

        Customer customer = customerOptional.get();
        Book book = bookOptional.get();
        PaymentMethod paymentMethod = paymentMethodOptional.get();

        Purchase purchase = Purchase
                .builder()
                    .customer(customer)
                    .price(book.getPrice())
                    .book(book)
                    .paymentMethod(paymentMethod)
                    .currency(GBP.toString())
                .build();
        purchaseService.save(purchase);

        purchaseResponseDTO = PurchaseResponseDTO.fromPurchase(purchase);
        return purchaseResponseDTO;
    }

    public List<PurchaseResponseDTO> findPurchasesByCustomerEmail(String email){
        return purchaseService.findPurchasesByCustomerEmail(email).stream().map(PurchaseResponseDTO::fromPurchase).collect(Collectors.toList());
    }
}
