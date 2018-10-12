package de.praktikant.bibliothek.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Source;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.books.BookSourceEntity;
import de.praktikant.bibliothek.api.backend.hibternate.repository.book.BookSourceRepository;
import de.praktikant.bibliothek.api.model.enums.MediaType;
import de.praktikant.bibliothek.api.model.mapper.SourceMapper;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.service.common.Severity;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
@Service
public class SourceService {

	@Autowired
    private BookSourceRepository bookSourceRepository;

	public Result<List<Source>> getAllSourcesByType(MediaType type) {
        List<Source> sourceList = null;
        switch (type) {
            case BOOK:
                List<BookSourceEntity> bookGenreList = bookSourceRepository.findAll();
                sourceList = SourceMapper.fromBookSourceList(bookGenreList);
                break;
        }

        return new Result<>(sourceList);
    }

    public Result<Source> getSourceByTypeAndId(MediaType type, Long id) {
        Result<Source> result = new Result<>();

        Source source = null;
        switch (type) {
            case BOOK:
                BookSourceEntity bookGenre = bookSourceRepository.findOne(id);
                source = SourceMapper.fromBookSource(bookGenre);
                break;
        }

        if (source != null) {
            result.setValue(source);
        } else {
            result.addMessage(Severity.ERROR,
                    String.format("No source with id %s was found.", id));
        }

        return result;
    }
}
