package de.praktikant.bibliothek.api.resources.response.customer;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerEntity;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

public class CustomerResponse extends BaseResponse {
    private CustomerEntity customer;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
