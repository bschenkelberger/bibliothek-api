package de.praktikant.bibliothek.api.resources;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.Source;
import de.praktikant.bibliothek.api.model.enums.MediaType;
import de.praktikant.bibliothek.api.resources.response.source.SourceListResponse;
import de.praktikant.bibliothek.api.resources.response.source.SourceResponse;
import de.praktikant.bibliothek.api.service.SourceService;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.utils.BaseResponseHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/sources")
public class SourceResource {
    @Autowired
    private SourceService service;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<SourceResponse> getSourceByName(@PathVariable("id") Long id,
                                                          @RequestParam("type") MediaType type) {
        Result<Source> result = service.getSourceByTypeAndId(type, id);

        SourceResponse response = new SourceResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setSource(result.getValue());
        }

        return ResponseEntity.ok(response);
    }
}
