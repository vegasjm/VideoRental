package com.videorental.service;

import com.videorental.model.CustomerDTO;

import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
public interface CustomerService {
    CustomerDTO getCustomerById(Long id);
    List<CustomerDTO> getAllCustomers();
    Boolean insertCustomer(String name, String surname);
}
