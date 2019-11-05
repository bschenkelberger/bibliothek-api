package de.praktikant.bibliothek.api.backend.hibernate.repository.book;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.books.BookEntity;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, Long> {
    List<BookEntity> findAllByOrderByName();
}
