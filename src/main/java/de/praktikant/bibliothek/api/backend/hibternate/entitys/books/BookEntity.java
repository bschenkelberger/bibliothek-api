package de.praktikant.bibliothek.api.backend.hibternate.entitys.books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Genre;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.Source;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerLendBookEntity;

import javax.persistence.*;

import java.util.List;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "book")
public class BookEntity {
    private Long id;
    private String name;
    private Boolean lend;
    private Boolean deleted;
    private Genre genre;
    private Source source;
    private List<CustomerLendBookEntity> customerLendBookEntities;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "book_name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "book_lend", nullable = false)
    public Boolean getLend() {
        return lend;
    }

    public void setLend(Boolean lend) {
        this.lend = lend;
    }

    @Column(name = "book_deleted", nullable = false)
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @ManyToOne(targetEntity = BookGenreEntity.class)
    @JoinColumn(name = "book_genre_id", nullable = false)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @ManyToOne(targetEntity = BookSourceEntity.class)
    @JoinColumn(name = "book_source_id", nullable = false)
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "book", targetEntity = CustomerLendBookEntity.class)
    public List<CustomerLendBookEntity> getCustomerLendBookEntities() {
        return customerLendBookEntities;
    }

    public void setCustomerLendBookEntities(List<CustomerLendBookEntity> customerLendBookEntities) {
        this.customerLendBookEntities = customerLendBookEntities;
    }
}
