package com.jesusgsdev.dtos;

import com.jesusgsdev.entities.Customer;
import com.jesusgsdev.entities.Purchase;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CustomerDTO extends BaseDTO {

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
    @Column(length = 150)
    private String email;

    private List<Purchase> purchases;

    public CustomerDTO() { }

    public CustomerDTO(@NotEmpty @Length(max = 140) String name, @NotEmpty @Length(max = 140) String surname, @NotEmpty @Length(max = 300) String address, @NotEmpty @Email @Length(max = 150) String email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
    }

    private CustomerDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.address = builder.address;
        this.email = builder.email;
        this.purchases = builder.purchases;
    }

    public static CustomerDTO fromCustomer(Customer customer){
        return CustomerDTO.newCustomerDTO()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .build();
    }

    public static Builder newCustomerDTO() {
        return new Builder();
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
        if (!(o instanceof CustomerDTO)) return false;

        CustomerDTO customer = (CustomerDTO) o;

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


    public static final class Builder {
        private Long id;
        private @NotEmpty @Length(max = 140) String name;
        private @NotEmpty @Length(max = 140) String surname;
        private @NotEmpty @Length(max = 300) String address;
        private @NotEmpty @Email @UniqueElements @Length(max = 150) String email;
        private List<Purchase> purchases;

        private Builder() {
        }

        public CustomerDTO build() {
            return new CustomerDTO(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder purchases(List<Purchase> purchases) {
            this.purchases = purchases;
            return this;
        }
    }
}
