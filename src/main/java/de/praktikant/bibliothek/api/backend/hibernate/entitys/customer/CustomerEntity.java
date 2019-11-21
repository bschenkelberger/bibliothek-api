package de.praktikant.bibliothek.api.backend.hibernate.entitys.customer;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "customer")
public class CustomerEntity {
    private Long id;
    private String name;
    private String firstName;
    private List<CustomerLendBookEntity> customerLendBooks;
    private Boolean admin;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "customer_name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "customer_first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name = "customer_admin", nullable = false)
    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "customer", targetEntity = CustomerLendBookEntity.class)
    public List<CustomerLendBookEntity> getCustomerLendBooks() {
        return customerLendBooks;
    }

    public void setCustomerLendBooks(List<CustomerLendBookEntity> customerLendBooks) {
        this.customerLendBooks = customerLendBooks;
    }

}
