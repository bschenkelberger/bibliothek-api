package de.praktikant.bibliothek.api.resources.response.book;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookEntity;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse extends BaseResponse {
    private BookEntity book;

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
