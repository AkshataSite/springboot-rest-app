package com.oracle.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oracle.dao.CustomerDao;
import com.oracle.exception.CustomerExistsException;
import com.oracle.model.Customer;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;

    @Override
    @Transactional
    public void addCustomer(Customer cust) {
        Customer existing = dao.readCustomerByEmail(cust.getEmail());
        if (existing != null) {
            throw new CustomerExistsException("Customer with email " + cust.getEmail() + " already exists.");
        }
        dao.createCustomer(cust);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return dao.readCustomerByEmail(email);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return dao.readAllCustomers();
    }

    @Override
    @Transactional
    public void updateCustomers(Customer cust) {
        dao.updateCustomer(cust);
    }

    @Override
    @Transactional
    public void deleteCustomers(String email) {
        dao.deleteCustomerByEmail(email);
    }
}
