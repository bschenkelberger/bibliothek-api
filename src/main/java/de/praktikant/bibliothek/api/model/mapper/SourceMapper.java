package de.praktikant.bibliothek.api.model.mapper;

import java.util.ArrayList;
import java.util.List;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.Source;
import de.praktikant.bibliothek.api.backend.hibernate.entitys.books.BookSourceEntity;

public class SourceMapper {

	public static Source fromBookSource(BookSourceEntity bookSourceEntity) {
        Source source = null;
        if (bookSourceEntity != null) {
            source = new Source();
            source.setId(bookSourceEntity.getId());
            source.setName(bookSourceEntity.getName());
        }
        return source;
    }

    public static List<Source> fromBookSourceList(List<BookSourceEntity> bookSourceEntityList) {
        List<Source> sourceList = new ArrayList<>();

        for (BookSourceEntity bookSourceEntity : bookSourceEntityList) {
            sourceList.add(fromBookSource(bookSourceEntity));
        }

        return sourceList;
    }

    public static BookSourceEntity toBookSource(Source source) {
        BookSourceEntity bookSourceEntity = null;
        if (source != null) {
            bookSourceEntity = new BookSourceEntity();
            bookSourceEntity.setId(source.getId());
            bookSourceEntity.setName(source.getName());
        }
        return bookSourceEntity;
    }
}
