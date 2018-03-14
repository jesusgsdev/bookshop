package com.jesusgsdev.dtos;

import com.jesusgsdev.entities.Book;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class BookDTO extends BaseDTO {

    private Long id;

    @NotEmpty
    @Length(min = 9, max = 13)
    private String ISBN;

    @NotEmpty
    @Length(max = 140)
    private String title;

    @DecimalMax("199.99")
    @DecimalMin("0.0")
    private Double price;

    @NotEmpty
    @Length(max = 155)
    private String author;

    @Min(1)
    private Integer pages;

    @NotEmpty
    @Length(max = 140)
    private String provider;

    public BookDTO() { }

    public BookDTO(Long id, @NotEmpty @Length(max = 13) @UniqueElements String ISBN, @NotEmpty @Length(max = 140) String title, @DecimalMax("199.99") @DecimalMin("0.0") Double price, @NotEmpty @Length(max = 155) String author, @Min(1) Integer pages, @NotEmpty @Length(max = 140) String provider) {
        super();
        this.id = id;
        this.ISBN = ISBN;
        this.title = title;
        this.price = price;
        this.author = author;
        this.pages = pages;
        this.provider = provider;
    }

    public static BookDTO fromBook(final Book book){
        return BookDTO.newBookDTO()
                .id(book.getId())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .title(book.getTitle())
                .pages(book.getPages())
                .price(book.getPrice())
                .provider(book.getProvider())
                .build();
    }

    private BookDTO(Builder builder) {
        this.id = builder.id;
        this.ISBN = builder.ISBN;
        this.title = builder.title;
        this.price = builder.price;
        this.author = builder.author;
        this.pages = builder.pages;
        this.provider = builder.provider;
    }

    public static Builder newBookDTO() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDTO)) return false;

        BookDTO bookDTO = (BookDTO) o;

        return ISBN.equals(bookDTO.ISBN);
    }

    @Override
    public int hashCode() {
        return ISBN.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("isbn", ISBN)
                .append("title", title)
                .append("price", price)
                .append("author", author)
                .append("pages", pages)
                .append("provider", provider)
                .toString();
    }


    public static final class Builder {
        private Long id;
        private @NotEmpty @Length(max = 13) @UniqueElements String ISBN;
        private @NotEmpty @Length(max = 140) String title;
        private @DecimalMax("199.99") @DecimalMin("0.0") Double price;
        private @NotEmpty @Length(max = 155) String author;
        private @Min(1) Integer pages;
        private @NotEmpty @Length(max = 140) String provider;

        private Builder() {
        }

        public BookDTO build() {
            return new BookDTO(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder isbn(String ISBN) {
            this.ISBN = ISBN;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder pages(Integer pages) {
            this.pages = pages;
            return this;
        }

        public Builder provider(String provider) {
            this.provider = provider;
            return this;
        }
    }
}
