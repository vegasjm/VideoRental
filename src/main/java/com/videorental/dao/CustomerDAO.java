package com.videorental.dao;

import com.videorental.model.CustomerDTO;

import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
public interface CustomerDAO {
    CustomerDTO getCustomerById(Long id);
    List<CustomerDTO> getAllCustomers();
    Boolean insertCustomer(String name, String surname);
    Boolean updateBonus(Long customerId, int i);
}
