package de.praktikant.bibliothek.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.Genre;
import de.praktikant.bibliothek.api.model.enums.MediaType;
import de.praktikant.bibliothek.api.resources.response.genre.GenreListResponse;
import de.praktikant.bibliothek.api.resources.response.genre.GenreResponse;
import de.praktikant.bibliothek.api.service.GenreService;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.utils.BaseResponseHelper;

/**
 * <p>
 * Diese Klasse dient als ein REST-Controller und bietet Funktionen bzgl. {@linkplain Genre}s an.
 * </p>
 * Siehe auch JavaDoc zu {@linkplain BookResource}.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/genres")
public class GenreResource {
    @Autowired
    private GenreService service;

    /**
     * <p>
     * Reagiert auf die URL ".../genres?type=BOOK".
     * </p>
     * 
     * @param type nur {@linkplain MediaType}.BOOK möglich.
     * @return eine Liste aller vorkonfigurierter Genres, gepackt in eine Spring-ResponseEntity
     */
    @GetMapping
    public ResponseEntity<GenreListResponse> getGenre(@RequestParam("type") MediaType type) {
        Result<List<Genre>> result = service.getAllGenresByType(type);

        GenreListResponse response = new GenreListResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setGenres(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * <p>
     * Reagiert auf die URL ".../genres/&lt;id&gt;?type=BOOK".
     * </p>
     * 
     * @param id Identifikation eines Genres
     * @param type nur {@linkplain MediaType}.BOOKS möglich.
     * @return das gesuchte Genre, gepackt in eine Spring-ResponseEntity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreResponse> getGenreByName(@PathVariable("id") Long id, @RequestParam("type") MediaType type) {

        Result<Genre> result = service.getGenreByTypeAndId(type, id);

        GenreResponse response = new GenreResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setGenre(result.getValue());
        }

        return ResponseEntity.ok(response);
    }
}
