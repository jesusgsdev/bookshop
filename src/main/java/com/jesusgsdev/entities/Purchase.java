package com.jesusgsdev.entities;

import com.jesusgsdev.entities.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
