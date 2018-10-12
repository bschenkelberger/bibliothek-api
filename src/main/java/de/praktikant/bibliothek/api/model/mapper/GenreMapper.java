package de.praktikant.bibliothek.api.model.mapper;

import java.util.ArrayList;
import java.util.List;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Genre;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookGenreEntity;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
public class GenreMapper {

	public static Genre fromBookGenre(BookGenreEntity bookGenreEntity) {
        Genre genre = null;
        if (bookGenreEntity != null) {
            genre = new BookGenreEntity();
            genre.setId(bookGenreEntity.getId());
            genre.setName(bookGenreEntity.getName());
        }
        return genre;
    }

    public static List<Genre> fromBookGenreList(List<BookGenreEntity> bookGenreEntityList) {
        List<Genre> genreList = new ArrayList<>();

        for (BookGenreEntity bookGenreEntity : bookGenreEntityList) {
            genreList.add(fromBookGenre(bookGenreEntity));
        }

        return genreList;
    }

    public static BookGenreEntity toBookGenre(Genre genre) {
        BookGenreEntity bookGenreEntity = null;
        if (genre != null) {
            bookGenreEntity = new BookGenreEntity();
            bookGenreEntity.setId(genre.getId());
            bookGenreEntity.setName(genre.getName());
        }
        return bookGenreEntity;
    }
}
