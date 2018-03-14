package com.jesusgsdev.entities;

import com.jesusgsdev.entities.enums.PaymentMethod;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name = "purchase")
public class Purchase extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double price;

    @Valid
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    private PaymentMethod paymentMethod;

    private String currency;

    public Purchase() { }

    public Purchase(@Valid Customer customer, Double price, @Valid Book book, @NotNull PaymentMethod paymentMethod, String currency) {
        this.customer = customer;
        this.price = price;
        this.book = book;
        this.paymentMethod = paymentMethod;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
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
        if (!(o instanceof Purchase)) return false;

        Purchase purchase = (Purchase) o;

        if (id != null ? !id.equals(purchase.id) : purchase.id != null) return false;
        if (customer != null ? !customer.equals(purchase.customer) : purchase.customer != null) return false;
        if (price != null ? !price.equals(purchase.price) : purchase.price != null) return false;
        if (book != null ? !book.equals(purchase.book) : purchase.book != null) return false;
        if (paymentMethod != purchase.paymentMethod) return false;
        return currency != null ? currency.equals(purchase.currency) : purchase.currency == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("customer", customer)
                .append("price", price)
                .append("book", book)
                .append("paymentMethod", paymentMethod)
                .append("currency", currency)
                .toString();
    }
}
