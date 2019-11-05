package de.praktikant.bibliothek.api.resources.response.customer;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerLendBookEntity;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerLendBookResponse extends BaseResponse {
    private CustomerLendBookEntity customerLendBooksEntity;

    public CustomerLendBookEntity getCustomerLendBooksEntity() {
        return customerLendBooksEntity;
    }

    public void setCustomerLendBooksEntity(CustomerLendBookEntity customer) {
        this.customerLendBooksEntity = customer;
    }
}
