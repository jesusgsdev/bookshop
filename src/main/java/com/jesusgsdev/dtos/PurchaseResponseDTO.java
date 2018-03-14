package com.jesusgsdev.dtos;

import com.jesusgsdev.entities.Purchase;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PurchaseResponseDTO extends BaseDTO {

    private Long id;

    private Double price;

    private BookDTO book;

    private String paymentMethod;

    private String currency;

    public PurchaseResponseDTO() { }

    public PurchaseResponseDTO(Long id, Double price, BookDTO book, String paymentMethod, String currency) {
        super();
        this.id = id;
        this.price = price;
        this.book = book;
        this.paymentMethod = paymentMethod;
        this.currency = currency;
    }

    private PurchaseResponseDTO(Builder builder) {
        this.id = builder.id;
        this.price = builder.price;
        this.book = builder.book;
        this.paymentMethod = builder.paymentMethod;
        this.currency = builder.currency;
    }

    public static PurchaseResponseDTO fromPurchase(Purchase purchase){
        return PurchaseResponseDTO.newPurchaseResponseDTO()
                .price(purchase.getPrice())
                .book(BookDTO.fromBook(purchase.getBook()))
                .paymentMethod(purchase.getPaymentMethod().toString())
                .currency(purchase.getCurrency())
                .build();
    }

    public static Builder newPurchaseResponseDTO() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseResponseDTO)) return false;

        PurchaseResponseDTO that = (PurchaseResponseDTO) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("price", price)
                .append("book", book)
                .append("paymentMethod", paymentMethod)
                .append("currency", currency)
                .toString();
    }


    public static final class Builder {
        private Long id;
        private CustomerDTO customer;
        private Double price;
        private BookDTO book;
        private String paymentMethod;
        private String currency;

        private Builder() {
        }

        public PurchaseResponseDTO build() {
            return new PurchaseResponseDTO(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder customer(CustomerDTO customer) {
            this.customer = customer;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder book(BookDTO book) {
            this.book = book;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }
    }
}
