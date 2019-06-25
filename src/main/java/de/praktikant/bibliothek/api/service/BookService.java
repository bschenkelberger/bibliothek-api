package de.praktikant.bibliothek.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Genre;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.Source;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookEntity;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerEntity;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerLendBookEntity;
import de.praktikant.bibliothek.api.backend.hibternate.repository.book.BookRepository;
import de.praktikant.bibliothek.api.backend.hibternate.repository.customer.CustomerLendBookRepository;
import de.praktikant.bibliothek.api.model.enums.MediaType;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.service.common.Severity;

@Service
public class BookService {
    @Autowired
    private CustomerLendBookRepository lendBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private SourceService sourceService;

    public Result<List<BookEntity>> getAllBooks() {
        List<BookEntity> bookList = bookRepository.findAllByOrderByName();

        return new Result<>(bookList);
    }

    public Result<BookEntity> getBookById(Long id) {
        Result<BookEntity> result = new Result<>();
        BookEntity book = bookRepository.findOne(id);

        if (book != null) {
            result.setValue(book);
        }
        else {
            result.addMessage(Severity.ERROR,
                    String.format("No book with id %s was found.", id));
        }

        return result;
    }

    public Result<BookEntity> addBook(BookEntity book) {
        Result<BookEntity> result = new Result<>();

        book.setLend(Boolean.FALSE);
        book.setDeleted(Boolean.FALSE);

        Result<Genre> genreResult = genreService.getGenreByTypeAndId(MediaType.BOOK, book.getGenre().getId());
        if (genreResult.getValue() != null) {
            book.setGenre(genreResult.getValue());
        } else {
            result.getErrorMessages().addAll(genreResult.getErrorMessages());
        }

        Result<Source> sourceResult = sourceService.getSourceByTypeAndId(MediaType.BOOK, book.getSource().getId());
        if (sourceResult.getValue() != null) {
            book.setSource(sourceResult.getValue());
        } else {
            result.getErrorMessages().addAll(sourceResult.getErrorMessages());
        }

        try {
            BookEntity savedBook = bookRepository.save(book);
            result.setValue(savedBook);
        }
        catch (RuntimeException ex) {
            result.addMessage(Severity.ERROR, ex.getCause().getMessage());
        }

        return result;
    }

    public Result<BookEntity> updateBook(BookEntity book) {
        Result<BookEntity> result = new Result<>();

        try {
            BookEntity updatedBook = bookRepository.save(book);
            result.setValue(updatedBook);
        }
        catch (RuntimeException ex) {
            result.addMessage(Severity.ERROR, ex.getCause().getMessage());
        }

        return result;
    }

    public Result<BookEntity> deleteBook(Long id) {
        BookEntity book = bookRepository.findOne(id);

        if (Boolean.FALSE.equals(book.getLend())) {
            book.setDeleted(Boolean.TRUE);
            book = bookRepository.save(book);
        }

        return new Result<>(book);
    }

    public Result<List<CustomerLendBookEntity>> getHistory(Long bookId) {
        List<CustomerLendBookEntity> lendBookHistoryList = lendBookRepository.findByBookId(bookId);

        return new Result<>(lendBookHistoryList);
    }

    public Result<CustomerLendBookEntity> lendBook(Long customerId, Long movieId) {
        Result<CustomerEntity> customerResult = customerService.getCustomerById(customerId);
        Result<BookEntity> bookResult = getBookById(movieId);

        Result<CustomerLendBookEntity> result = new Result<>();
        result.getErrorMessages().addAll(customerResult.getErrorMessages());
        result.getErrorMessages().addAll(bookResult.getErrorMessages());

        BookEntity book = bookResult.getValue();
        CustomerEntity customer = customerResult.getValue();

        if (customer != null && book != null) {
            if (Boolean.FALSE.equals(book.getLend())) {
                book.setLend(Boolean.TRUE);

                CustomerLendBookEntity lendBookHistory = new CustomerLendBookEntity();
                lendBookHistory.setCustomer(customer);
                lendBookHistory.setBook(book);
                lendBookHistory.setLendOnDate(LocalDate.now());

                try {
                    lendBookHistory = lendBookRepository.save(lendBookHistory);
                    result.setValue(lendBookHistory);
                } catch (RuntimeException ex) {
                    result.addMessage(Severity.ERROR, ex.getCause().getMessage());
                }
            } else {
                result.addMessage(Severity.ERROR, String.format("%s is already lend.", book.getName()));
            }
        }

        return result;
    }

    public Result<CustomerLendBookEntity> returnBook(Long bookId) {
        Result<CustomerLendBookEntity> result = new Result<>();

        CustomerLendBookEntity lendBookEntity = lendBookRepository.findLendByBookId(bookId);

        if (lendBookEntity != null) {
            lendBookEntity.setReturnedOnDate(LocalDate.now());
            lendBookEntity.getBook().setLend(Boolean.FALSE);

            try {
                lendBookEntity = lendBookRepository.save(lendBookEntity);
                result.setValue(lendBookEntity);
            } catch (RuntimeException ex) {
                result.addMessage(Severity.ERROR, ex.getCause().getMessage());
            }
        } else {
            result.addMessage(Severity.WARNING, "The book is currently not awarded");
        }

        return result;
    }
}
