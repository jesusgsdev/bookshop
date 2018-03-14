package com.jesusgsdev.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PurchaseRequestDTO extends BaseDTO {

    @NotEmpty
    private String customerEmail;

    @NotEmpty
    private String bookISBN;

    @NotNull
    private String paymentMethod;

    public PurchaseRequestDTO() { }

    public PurchaseRequestDTO(@NotEmpty String customerEmail, @NotEmpty String bookISBN, @NotNull String paymentMethod) {
        super();
        this.customerEmail = customerEmail;
        this.bookISBN = bookISBN;
        this.paymentMethod = paymentMethod;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("customerEmail", customerEmail)
                .append("bookISBN", bookISBN)
                .append("paymentMethod", paymentMethod)
                .toString();
    }
}
