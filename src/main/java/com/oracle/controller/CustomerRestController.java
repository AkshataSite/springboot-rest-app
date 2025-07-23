package com.oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oracle.exception.CustomerExistsException;
import com.oracle.model.Customer;
import com.oracle.service.CustomerService;

@RestController
@RequestMapping(path = "/customer-api")
public class CustomerRestController {

    @Autowired
    private CustomerService service;

    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@RequestBody Customer cust) {
        service.addCustomer(cust);
        return new ResponseEntity<>("Customer added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        Customer customer = service.findCustomerByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public List<Customer> getCustomers() {
        return service.findAllCustomers();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomers(@RequestParam String email) {
        service.deleteCustomers(email);
        return new ResponseEntity<>("Customer deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomers(@RequestBody Customer cust) {
        service.updateCustomers(cust);
        return new ResponseEntity<>("Customer updated", HttpStatus.OK);
    }

    @ExceptionHandler(CustomerExistsException.class)
    public ResponseEntity<String> handleUnique(CustomerExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
