package de.praktikant.bibliothek.api.backend.hibternate.repository.book;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookGenreEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
public interface BookGenreRepository extends CrudRepository<BookGenreEntity, Long> {
    List<BookGenreEntity> findAll();

    BookGenreEntity findByName(@Param("name") String name);
}
