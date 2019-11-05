package de.praktikant.bibliothek.api.backend.hibernate.repository.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.books.BookSourceEntity;

import java.util.List;

public interface BookSourceRepository extends CrudRepository<BookSourceEntity, Long> {
    List<BookSourceEntity> findAll();

    BookSourceEntity findByName(@Param("name") String name);
}
