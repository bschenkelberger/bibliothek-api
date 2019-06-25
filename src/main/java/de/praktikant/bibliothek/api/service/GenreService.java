package de.praktikant.bibliothek.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Genre;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookGenreEntity;
import de.praktikant.bibliothek.api.backend.hibternate.repository.book.BookGenreRepository;
import de.praktikant.bibliothek.api.model.enums.MediaType;
import de.praktikant.bibliothek.api.model.mapper.GenreMapper;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.service.common.Severity;

@Service
public class GenreService {

	@Autowired
    private BookGenreRepository bookGenreRepository;
    public Result<List<Genre>> getAllGenresByType(MediaType type) {
        List<Genre> genreList = null;
        switch (type) {
            case BOOK:
                List<BookGenreEntity> bookGenreEntityList = bookGenreRepository.findAll();
                genreList = GenreMapper.fromBookGenreList(bookGenreEntityList);
                break;
        }

        return new Result<>(genreList);
    }

    public Result<Genre> getGenreByTypeAndId(MediaType type, Long id) {
        Result<Genre> result = new Result<>();

        Genre genre = null;
        switch (type) {
            case BOOK:
                BookGenreEntity bookGenreEntity = bookGenreRepository.findOne(id);
                genre = GenreMapper.fromBookGenre(bookGenreEntity);
                break;
        }

        if (genre != null) {
            result.setValue(genre);
        } else {
            result.addMessage(Severity.ERROR,
                    String.format("No genre with id %s was found.", id));
        }

        return result;
    }
}
