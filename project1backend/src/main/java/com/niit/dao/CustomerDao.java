package com.niit.dao;

import java.util.List;

import com.niit.models.Customer;


public interface CustomerDao {
void registerCustomer(Customer customer);
boolean isEmailUnique(String email);
/*List<Eclass> getRecords();*/

}
