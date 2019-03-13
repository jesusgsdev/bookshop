package com.jesusgsdev.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Length(min  = 9, max = 13)
    @Column(length = 13, unique = true)
    private String isbn;

    @NotEmpty
    @Length(max = 140)
    @Column(length = 140)
    private String title;

    @DecimalMax("199.99")
    @DecimalMin("0.0")
    private Double price;

    @NotEmpty
    @Length(max = 155)
    @Column(length = 155)
    private String author;

    @Min(1)
    private Integer pages;

    @NotEmpty
    @Length(max = 140)
    @Column(length = 140)
    private String provider;

}
