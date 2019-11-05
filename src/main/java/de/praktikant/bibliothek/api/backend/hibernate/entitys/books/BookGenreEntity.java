package de.praktikant.bibliothek.api.backend.hibernate.entitys.books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.Genre;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "book_genre")
public class BookGenreEntity extends Genre {
    private List<BookEntity> bookEntityList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "genre", targetEntity = BookEntity.class)
    public List<BookEntity> getBookEntityList() {
        return bookEntityList;
    }

    public void setBookEntityList(List<BookEntity> bookEntityList) {
        this.bookEntityList = bookEntityList;
    }
}
