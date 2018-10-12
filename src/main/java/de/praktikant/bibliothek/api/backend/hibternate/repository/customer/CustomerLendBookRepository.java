package de.praktikant.bibliothek.api.backend.hibternate.repository.customer;

import de.praktikant.bibliothek.api.backend.hibternate.entitys.customer.CustomerLendBookEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Bjoern Schenkelberger, Postbank Systems AG
 */
public interface CustomerLendBookRepository extends PagingAndSortingRepository<CustomerLendBookEntity, Long> {
    List<CustomerLendBookEntity> findAll();

    @Query("SELECT clb FROM CustomerLendBookEntity clb WHERE clb.book.id = :bookId ORDER BY clb.id DESC")
    List<CustomerLendBookEntity> findByBookId(@Param("bookId") Long bookId);

    @Query("SELECT clb FROM CustomerLendBookEntity clb WHERE clb.customer.id = :customerId ORDER BY clb.id DESC")
    List<CustomerLendBookEntity> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT clb FROM CustomerLendBookEntity clb WHERE clb.book.id = :bookId AND clb.returnedOnDate=null")
    CustomerLendBookEntity findLendByBookId(@Param("bookId") Long bookId);
}