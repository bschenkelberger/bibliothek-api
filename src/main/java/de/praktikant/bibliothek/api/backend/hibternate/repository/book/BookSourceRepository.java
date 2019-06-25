package de.praktikant.bibliothek.api.backend.hibternate.repository.book;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookSourceEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookSourceRepository extends CrudRepository<BookSourceEntity, Long> {
    List<BookSourceEntity> findAll();

    BookSourceEntity findByName(@Param("name") String name);
}
