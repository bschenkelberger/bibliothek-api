package de.praktikant.bibliothek.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.books.BookEntity;
import de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerLendBookEntity;
import de.praktikant.bibliothek.api.resources.response.book.BookListResponse;
import de.praktikant.bibliothek.api.resources.response.book.BookResponse;
import de.praktikant.bibliothek.api.resources.response.book.LendBookHistoryListResponse;
import de.praktikant.bibliothek.api.resources.response.book.LendBookHistoryResponse;
import de.praktikant.bibliothek.api.service.BookService;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.utils.BaseResponseHelper;

/**
 * <p>
 * Diese Klasse ist ein Spring REST-Controller und bietet zahlreiche Bibliotheks-Funktionen bzgl. Büchern an.
 * </p>
 * <p>
 * Unter Spring behandeln RESTful Webservices HTTP Requests mit sog. Controllern, die mit der @RestController Annotation gekennzeichnet
 * werden.
 * </p>
 * <p>
 * Die @RequestMapping Annotation stellt sicher, dass HTTP Requests zur URL "../books" von dieser Klasse bearbeitet werden.
 * </p>
 * <p>
 * Die @RequestMapping spezifiziert keine spezielle Methode, somit werden alle Http-Methoden von dieser Klasse bearbeitet.
 * </p>
 * <p>
 * Die @CrossOrigin Annotation ermöglicht ein "Cross-origin resource sharing" (CORS) [siehe
 * https://docs.spring.io/spring/docs/4.2.2.RELEASE/spring-framework-reference/htmlsingle/#cors]
 * </p>
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/books")
public class BookResource {
    @Autowired
    private BookService service;

    /**
     * Die zusammengesetzte Annotation @GetMapping, eine Kurzform von @RequestMapping(value="/books", method = RequestMethod.GET), bewirkt, dass diese
     * Methode getBooks() auf den Http-Request GET .../books reagiert. .
     * 
     * @return Eine Liste von BookEntities, gepackt in eine Spring-ResponseEntity.
     */
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

    /**
     * Die zusammengesetzte Annotation @GetMapping(value = "/{book-id}"), eine Kurzform von @RequestMapping(value="/books/{book-id}", method =
     * RequestMethod.GET), bewirkt, dass diese
     * Methode getBookById() auf den Http-Request GET .../books/{book-id} reagiert.
     * 
     * @param bookId Identifikation eines Buches
     * @return Die gesuchte BookEntity, gepackt in eine Spring-ResponseEntity.
     */
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

    /**
     * Mit der POST-Methode wird ein Insert eines Buches in die Datenbank ausgeführt.
     * 
     * @param book das einzufügende Buch-Objekt
     * @return Die BookEntity, gepackt in eine Spring-ResponseEntity.
     */
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

    /**
     * Mit der PUT-Methode wird ein Update eines Buches in der Datenbank ausgeführt.
     * 
     * @param book das geänderte Buch-Objekt
     * @return Die BookEntity, gepackt in eine Spring-ResponseEntity.
     */
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

    /**
     * Mit der PELETE-Methode wird das Buch mit der &lt;book-id&gt; in der Datenbank "gelöscht".
     * 
     * @param bookId die ID des zu löschenden Buch-Objekts
     * @return Die gelöschte BookEntity, gepackt in eine Spring-ResponseEntity.
     */
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

    /**
     * Diese Methode liefert eine Liste von Kunden-Buch-Beziehungen zu einem Buch (= Kunden, die dieses Buch einmal entliehen hatten oder noch haben).
     * 
     * @param bookId die ID des betrachteten Buch-Objekts
     * @return Eine Liste von CustomerLendBookEntities, gepackt in eine Spring-ResponseEntity.
     */
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

    /**
     * Diese Methode erzeugt eine Ausleihe des Buches mit der ID bookId an den Kunden mit der ID customerId.
     * 
     * @param bookId Identifikation des Buches
     * @param customerId Identifikation des Kuden
     * @return Eine CustomerLendBookEntity, gepackt in eine SpringResponseEntity
     */
    @GetMapping(value = "/{book-id}/lend/{customer-id}")
    public ResponseEntity<LendBookHistoryResponse> lendBook(@PathVariable("book-id") Long bookId, @PathVariable("customer-id") Long customerId) {
        Result<CustomerLendBookEntity> result = service.lendBook(customerId, bookId);

        LendBookHistoryResponse response = new LendBookHistoryResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setLendBookHistory(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Diese Methode erzeugt die Rückgabe des Buches mit der ID bookId.
     * 
     * @param bookId Identifikation des Buches
     * @return Eine CustomerLendBookEntity, gepackt in eine SpringResponseEntity
     */
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
