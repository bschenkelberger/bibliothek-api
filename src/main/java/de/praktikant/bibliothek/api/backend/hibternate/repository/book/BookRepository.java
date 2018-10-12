package de.praktikant.bibliothek.api.backend.hibternate.repository.book;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookEntity;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
public interface BookRepository extends PagingAndSortingRepository<BookEntity, Long> {
    List<BookEntity> findAllByOrderByName();
}
