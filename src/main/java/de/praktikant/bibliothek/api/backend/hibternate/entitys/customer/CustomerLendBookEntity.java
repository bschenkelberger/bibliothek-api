package de.praktikant.bibliothek.api.backend.hibternate.entitys.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookEntity;

import javax.persistence.*;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "customer_lend_book")
public class CustomerLendBookEntity {
    private Long id;
    private LocalDate lendOnDate;
    private LocalDate returnedOnDate;
    private CustomerEntity customer;
    private BookEntity book;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_lend_book_id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "customer_lend_book_lend_on_date", nullable = false)
    public LocalDate getLendOnDate() {
        return lendOnDate;
    }

    public void setLendOnDate(LocalDate lendOnDate) {
        this.lendOnDate = lendOnDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "customer_lend_book_returned_on_date")
    public LocalDate getReturnedOnDate() {
        return returnedOnDate;
    }

    public void setReturnedOnDate(LocalDate returnedOnDate) {
        this.returnedOnDate = returnedOnDate;
    }

    @ManyToOne(targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customer_id", nullable = false)
    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    @ManyToOne(targetEntity = BookEntity.class)
    @JoinColumn(name = "book_id", nullable = false)
    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
