package de.praktikant.bibliothek.api.resources.response.genre;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Genre;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

import java.util.List;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreListResponse extends BaseResponse {
    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
