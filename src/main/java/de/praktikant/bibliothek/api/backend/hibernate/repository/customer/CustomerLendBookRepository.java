package de.praktikant.bibliothek.api.backend.hibernate.repository.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerLendBookEntity;

import java.util.List;

public interface CustomerLendBookRepository extends PagingAndSortingRepository<CustomerLendBookEntity, Long> {
    List<CustomerLendBookEntity> findAll();

    @Query("SELECT clb FROM CustomerLendBookEntity clb WHERE clb.book.id = :bookId ORDER BY clb.id DESC")
    List<CustomerLendBookEntity> findByBookId(@Param("bookId") Long bookId);

    @Query("SELECT clb FROM CustomerLendBookEntity clb WHERE clb.customer.id = :customerId ORDER BY clb.id DESC")
    List<CustomerLendBookEntity> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT clb FROM CustomerLendBookEntity clb WHERE clb.book.id = :bookId AND clb.returnedOnDate=null")
    CustomerLendBookEntity findLendByBookId(@Param("bookId") Long bookId);
}
