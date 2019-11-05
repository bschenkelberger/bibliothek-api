package de.praktikant.bibliothek.api.resources.response.source;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.Source;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceResponse extends BaseResponse {
    private Source source;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
