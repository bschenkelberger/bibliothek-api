package de.praktikant.bibliothek.api.resources.response.book;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.books.BookEntity;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookListResponse extends BaseResponse {
    private List<BookEntity> books = new ArrayList<>();

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
