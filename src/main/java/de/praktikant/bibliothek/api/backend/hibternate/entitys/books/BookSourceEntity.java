package de.praktikant.bibliothek.api.backend.hibternate.entitys.books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Source;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "book_source")
public class BookSourceEntity extends Source {
    private List<BookEntity> bookEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "source", targetEntity = BookEntity.class)
    public List<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public void setBookEntities(List<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }
}
