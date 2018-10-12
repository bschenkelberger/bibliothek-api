package de.praktikant.bibliothek.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerEntity;
import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerLendBookEntity;
import de.praktikant.bibliothek.api.backend.hibternate.repository.customer.CustomerLendBookRepository;
import de.praktikant.bibliothek.api.backend.hibternate.repository.customer.CustomerRepository;
import de.praktikant.bibliothek.api.service.common.Result;
import de.praktikant.bibliothek.api.service.common.Severity;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerLendBookRepository lendBookRepository;

    public Result<List<CustomerEntity>> getAllCustomer() {
        Result<List<CustomerEntity>> result = new Result<>();

        List<CustomerEntity> customerList = customerRepository.findAllByOrderByFirstName();
        result.setValue(customerList);

        return result;
    }

    public Result<CustomerEntity> getCustomerById(Long customerId) {
        Result<CustomerEntity> result = new Result<>();

        CustomerEntity customer = customerRepository.findOne(customerId);

        if (customer != null) {
            result.setValue(customer);
        } else {
            result.addMessage(Severity.ERROR,
                    String.format("No customer with id %s was found.", customerId));
        }

        return result;
    }

    public Result<CustomerEntity> addCustomer(CustomerEntity customer) {
        Result<CustomerEntity> result = new Result<>();

        try {
            CustomerEntity savedCustomer = customerRepository.save(customer);
            result.setValue(savedCustomer);
        }
        catch (RuntimeException ex) {
            result.addMessage(Severity.ERROR, ex.getCause().getMessage());
        }

        return result;
    }

    public Result<CustomerEntity> updateCustomer(CustomerEntity customer) {
        return addCustomer(customer);
    }

    public Result<List<CustomerLendBookEntity>> getLendBooks(Long customerId) {
        List<CustomerLendBookEntity> lendBooksHistoryList = lendBookRepository.findByCustomerId(customerId);

        return new Result<>(lendBooksHistoryList);
    }

}
