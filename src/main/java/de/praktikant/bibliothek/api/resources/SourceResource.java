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

import de.praktikant.bibliothek.api.backend.hibernate.entitys.Source;
import de.praktikant.bibliothek.api.model.enums.MediaType;
import de.praktikant.bibliothek.api.resources.response.source.SourceListResponse;
import de.praktikant.bibliothek.api.resources.response.source.SourceResponse;
import de.praktikant.bibliothek.api.service.SourceService;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.utils.BaseResponseHelper;

/**
 * <p>
 * Diese Klasse dient als ein REST-Controller und bietet Funktionen bzgl. {@linkplain Source}s (= Medientyp) an.
 * </p>
 * Siehe auch JavaDoc zu {@linkplain BookResource}.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/sources")
public class SourceResource {
    @Autowired
    private SourceService service;

    /**
     * <p>
     * Reagiert auf die URL ".../sources?type=BOOK".
     * </p>
     * 
     * @param type nur {@linkplain MediaType}.BOOKS möglich.
     * @return eine Liste aller vorkonfigurierter Sources, gepackt in eine Spring-ResponseEntity
     */
    @GetMapping
    public ResponseEntity<SourceListResponse> getSource(@RequestParam("type") MediaType type) {
        Result<List<Source>> result = service.getAllSourcesByType(type);

        SourceListResponse response = new SourceListResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setSources(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * <p>
     * Reagiert auf die URL ".../sources/&lt;id&gt;?type=BOOK".
     * </p>
     * 
     * @param id Identifikation einer Source
     * @param type nur {@linkplain MediaType}.BOOK möglich.
     * @return die gesuchte Source, gepackt in eine Spring-ResponseEntity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<SourceResponse> getSourceByName(@PathVariable("id") Long id, @RequestParam("type") MediaType type) {
        Result<Source> result = service.getSourceByTypeAndId(type, id);

        SourceResponse response = new SourceResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setSource(result.getValue());
        }

        return ResponseEntity.ok(response);
    }
}
