package com.oracle.service;

import java.util.List;
import com.oracle.model.Customer;

public interface CustomerService {
    void addCustomer(Customer cust);
    Customer findCustomerByEmail(String email);
    List<Customer> findAllCustomers();
    void updateCustomers(Customer cust);
    void deleteCustomers(String email);
}
