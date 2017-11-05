package com.videorental.persistence.dao;

import com.videorental.persistence.model.CustomerDTO;

import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
public interface CustomerDAO {
    CustomerDTO getCustomerById(Long id);
    List<CustomerDTO> getAllCustomers();
    Boolean insertCustomer(String name, String surname);
}
