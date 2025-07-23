package com.oracle.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.oracle.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createCustomer(Customer cust) {
        entityManager.persist(cust);
    }

    @Override
    public Customer readCustomerByEmail(String email) {
        String jpql = "SELECT c FROM Customer c WHERE c.email = :email";
        List<Customer> result = entityManager.createQuery(jpql, Customer.class)
                                             .setParameter("email", email)
                                             .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Customer> readAllCustomers() {
        String jpql = "SELECT c FROM Customer c";
        return entityManager.createQuery(jpql, Customer.class).getResultList();
    }

    @Override
    @Transactional
    public void updateCustomer(Customer cust) {
        entityManager.merge(cust);
    }

    @Override
    @Transactional
    public void deleteCustomerByEmail(String email) {
        Customer cust = readCustomerByEmail(email);
        if (cust != null) {
            entityManager.remove(cust);
        }
    }
}
