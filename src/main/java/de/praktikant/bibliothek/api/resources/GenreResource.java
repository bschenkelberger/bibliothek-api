package de.praktikant.bibliothek.api.resources;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Genre;
import de.praktikant.bibliothek.api.model.enums.MediaType;
import de.praktikant.bibliothek.api.resources.response.genre.GenreListResponse;
import de.praktikant.bibliothek.api.resources.response.genre.GenreResponse;
import de.praktikant.bibliothek.api.service.GenreService;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.utils.BaseResponseHelper;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreResponse> getGenreByName(@PathVariable("id") Long id,
                                                @RequestParam("type") MediaType type) {

        Result<Genre> result = service.getGenreByTypeAndId(type, id);

        GenreResponse response = new GenreResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setGenre(result.getValue());
        }

        return ResponseEntity.ok(response);
    }
}
