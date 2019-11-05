package de.praktikant.bibliothek.api.backend.hibernate.repository.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.books.BookGenreEntity;

import java.util.List;

public interface BookGenreRepository extends CrudRepository<BookGenreEntity, Long> {
    List<BookGenreEntity> findAll();

    BookGenreEntity findByName(@Param("name") String name);
}
