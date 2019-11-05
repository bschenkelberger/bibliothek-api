package de.praktikant.bibliothek.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerEntity;
import de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerLendBookEntity;
import de.praktikant.bibliothek.api.resources.response.book.LendBookHistoryListResponse;
import de.praktikant.bibliothek.api.resources.response.customer.CustomerListResponse;
import de.praktikant.bibliothek.api.resources.response.customer.CustomerResponse;
import de.praktikant.bibliothek.api.service.CustomerService;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.utils.BaseResponseHelper;

/**
 * <p>
 * Diese Klasse dient als ein REST-Controller und bietet zahlreiche Bibliotheks-Funktionen zur Kundenverwaltung an.
 * </p>
 * Siehe auch JavaDoc zu {@linkplain BookResource}.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/customer")
public class CustomerResource {
    @Autowired
    private CustomerService customerService;

    /**
     * @return Eine Liste von CustomerEntities, gepackt in eine Spring-ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<CustomerListResponse> getCustomer() {
        Result<List<CustomerEntity>> result = customerService.getAllCustomer();

        CustomerListResponse response = new CustomerListResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setCustomer(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * @param id Identifikation eines Kunden
     * @return Die gesuchte CustomerEntity, gepackt in eine Spring-ResponseEntity.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") Long id) {
        Result<CustomerEntity> result = customerService.getCustomerById(id);

        CustomerResponse response = new CustomerResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setCustomer(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Mit der POST-Methode wird ein Insert eines Kunden in die Datenbank ausgeführt.
     * 
     * @param customer der anzulegende Kunde
     * @return Die CustomerEntity, gepackt in eine Spring-ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<CustomerResponse> postCustomer(@RequestBody CustomerEntity customer) {
        Result<CustomerEntity> result = customerService.addCustomer(customer);

        CustomerResponse response = new CustomerResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setCustomer(result.getValue());
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Mit der PUT-Methode wird ein Update eines Kunden in der Datenbank ausgeführt.
     * 
     * @param customer das geänderte Kunden-Objekt
     * @return Die CutomerEntity, gepackt in eine Spring-ResponseEntity.
     */
    @PutMapping
    public ResponseEntity<CustomerResponse> putCustomer(@RequestBody CustomerEntity customer) {
        Result<CustomerEntity> result = customerService.updateCustomer(customer);

        CustomerResponse response = new CustomerResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setCustomer(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Diese Methode liefert eine Liste von Kunden-Buch-Beziehungen zu einem Kunden (= Bücher, die dieser Kunde einmal entliehen hatte oder noch
     * hat).
     * 
     * @param id Identifikation des Kunden
     * @return Eine Liste von CustomerLendBookEntities, gepackt in eine Spring-ResponseEntity.
     */
    @GetMapping(value = "/{id}/books")
    public ResponseEntity<LendBookHistoryListResponse> getLendBooks(@PathVariable("id") Long id) {
        Result<List<CustomerLendBookEntity>> result = customerService.getLendBooks(id);

        LendBookHistoryListResponse response = new LendBookHistoryListResponse();
        BaseResponseHelper.mapResultToResponse(response, result);

        if (response.isSuccessful()) {
            response.setLendBookHistory(result.getValue());
        }

        return ResponseEntity.ok(response);
    }

}
