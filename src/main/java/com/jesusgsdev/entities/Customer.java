package com.jesusgsdev.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Length(max = 140)
    @Column(length = 140)
    private String name;

    @NotEmpty
    @Length(max = 140)
    @Column(length = 140)
    private String surname;

    @NotEmpty
    @Length(max = 300)
    @Column(length = 300)
    private String address;

    @NotEmpty
    @Email
    @Length(max = 150)
    @Column(length = 150, unique = true)
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;

}
