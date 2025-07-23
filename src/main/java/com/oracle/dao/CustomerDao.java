package com.oracle.dao;

import java.util.List;
import com.oracle.model.Customer;

public interface CustomerDao {
    void createCustomer(Customer cust);
    Customer readCustomerByEmail(String email);
    List<Customer> readAllCustomers();
    void updateCustomer(Customer cust);
    void deleteCustomerByEmail(String email);
}
