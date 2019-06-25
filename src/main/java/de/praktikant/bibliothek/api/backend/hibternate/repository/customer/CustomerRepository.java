package de.praktikant.bibliothek.api.backend.hibternate.repository.customer;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerEntity;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {
    List<CustomerEntity> findAllByOrderByFirstName();
}
