package com.videorental.persistence.service;

import com.videorental.persistence.model.CustomerDTO;

import java.util.List;

/**
 * Created by vegasjm on 01/08/2016.
 */
public interface CustomerService {
    CustomerDTO getCustomerById(Long id);
    List<CustomerDTO> getAllCustomers();
    Boolean insertCustomer(String name, String surname);
}
