package com.videorental.persistence.service;

import com.videorental.persistence.dao.CustomerDAO;
import com.videorental.persistence.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vegasjm on 01/08/2016.
 */
@Component
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    @Qualifier("customerDAOImpl")
    private CustomerDAO customerDAO;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public Boolean insertCustomer(String name, String surname) {
        return customerDAO.insertCustomer(name,surname);
    }
}
