package de.praktikant.bibliothek.api.resources.response.source;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.Source;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceListResponse extends BaseResponse {
    private List<Source> sources;

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
