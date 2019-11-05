package de.praktikant.bibliothek.api.backend.hibernate.repository.customer;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerEntity;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {
    List<CustomerEntity> findAllByOrderByFirstName();
}
