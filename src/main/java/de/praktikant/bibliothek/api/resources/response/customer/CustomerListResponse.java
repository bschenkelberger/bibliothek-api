package de.praktikant.bibliothek.api.resources.response.customer;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerEntity;
import de.praktikant.bibliothek.api.resources.response.BaseResponse;

import java.util.List;

public class CustomerListResponse extends BaseResponse {
    private List<CustomerEntity> customer;

    public List<CustomerEntity> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomerEntity> customer) {
        this.customer = customer;
    }
}
