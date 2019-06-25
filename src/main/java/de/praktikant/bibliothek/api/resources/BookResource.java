package de.praktikant.bibliothek.api.resources;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookEntity;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerLendBookEntity;
import de.praktikant.bibliothek.api.resources.response.book.BookListResponse;
import de.praktikant.bibliothek.api.resources.response.book.BookResponse;
import de.praktikant.bibliothek.api.resources.response.book.LendBookHistoryListResponse;
import de.praktikant.bibliothek.api.resources.response.book.LendBookHistoryResponse;
import de.praktikant.bibliothek.api.service.BookService;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.utils.BaseResponseHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/books")
public class BookResource {
    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<BookListResponse> getBooks() {
        Result<List<BookEntity>> result = service.getAllBooks();

        BookListResponse response = new BookListResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setBooks(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{book-id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("book-id") Long bookId) {
        Result<BookEntity> result = service.getBookById(bookId);

        BookResponse response = new BookResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setBook(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BookResponse> postBook(@RequestBody BookEntity book) {
        Result<BookEntity> result = service.addBook(book);

        BookResponse response = new BookResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setBook(result.getValue());
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookResponse> putBook(@RequestBody BookEntity book) {
        Result<BookEntity> result = service.updateBook(book);

        BookResponse response = new BookResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setBook(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{book-id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable("book-id") Long bookId) {
        Result<BookEntity> result = service.deleteBook(bookId);

        BookResponse response = new BookResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setBook(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{book-id}/history")
    public ResponseEntity<LendBookHistoryListResponse> getHistory(@PathVariable("book-id") Long bookId) {
        Result<List<CustomerLendBookEntity>> result = service.getHistory(bookId);

        LendBookHistoryListResponse response = new LendBookHistoryListResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setLendBookHistory(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{book-id}/lend/{customer-id}")
    public ResponseEntity<LendBookHistoryResponse> lendBook(@PathVariable("book-id") Long bookId,
                                                            @PathVariable("customer-id") Long customerId) {
        Result<CustomerLendBookEntity> result = service.lendBook(customerId, bookId);

        LendBookHistoryResponse response = new LendBookHistoryResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setLendBookHistory(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{book-id}/return")
    public ResponseEntity<LendBookHistoryResponse> returnBook(@PathVariable("book-id") Long bookId) {
        Result<CustomerLendBookEntity> result = service.returnBook(bookId);

        LendBookHistoryResponse response = new LendBookHistoryResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setLendBookHistory(result.getValue());
        }

        return ResponseEntity.ok(response);
    }
}
