package de.praktikant.bibliothek.api.resources.response.genre;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Genre;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreResponse extends BaseResponse {
    private Genre genre;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
