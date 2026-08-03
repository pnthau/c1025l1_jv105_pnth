package com.example.manager_customer.service;

import com.example.manager_customer.entity.Customer;

import java.util.HashMap;
import java.util.List;

public interface ICustomerService {
  List<Customer> findAll();
  Customer findById(int id);
  boolean save(Customer customer);
}
