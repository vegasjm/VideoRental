package com.videorental.controller;

import com.videorental.model.CustomerDTO;
import com.videorental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
@RestController
@RequestMapping("api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET,  produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    CustomerDTO getCustomer(@RequestParam Long id) throws IOException {
        return customerService.getCustomerById(id);
    }

    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET,  produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<CustomerDTO> getAllCustomers() throws IOException {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "/insertCustomer", method = RequestMethod.POST,  produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Boolean insertCustomer(@RequestParam("name") String name, @RequestParam("surname") String surname) throws IOException {
        return customerService.insertCustomer(name,surname);
    }
}
