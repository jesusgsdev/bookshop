package com.jesusgsdev.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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

    public Customer() { }

    public Customer(@NotEmpty @Length(max = 140) String name, @NotEmpty @Length(max = 140) String surname, @NotEmpty @Length(max = 300) String address, @NotEmpty @Email @Length(max = 150) String email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        return email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("surname", surname)
                .append("address", address)
                .append("email", email)
                .toString();
    }
}
