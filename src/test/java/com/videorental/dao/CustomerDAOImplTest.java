package com.videorental.dao;

import com.videorental.model.CustomerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
/**
 * Created by vegasjm on 07/11/2017.
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.videorental")
@SpringBootTest
public class CustomerDAOImplTest {

    @Autowired
    CustomerDAOImpl customerDAO;

    @Test
    public void testGetCustomerById(){
        CustomerDTO customer = customerDAO.getCustomerById(1L);
        assertNotNull(customer);
        assertTrue(customer.getFirstName().equals("DONALD"));
        assertTrue(customer.getLastName().equals("TRUMP"));

        customer = customerDAO.getCustomerById(2L);
        assertNotNull(customer);
        assertTrue(customer.getFirstName().equals("BARACK"));
        assertTrue(customer.getLastName().equals("OBAMA"));

        customer = customerDAO.getCustomerById(3L);
        assertNotNull(customer);
        assertTrue(customer.getFirstName().equals("BILL"));
        assertTrue(customer.getLastName().equals("CLINTON"));

        customer = customerDAO.getCustomerById(4L);
        assertNotNull(customer);
        assertTrue(customer.getFirstName().equals("VLADIMIR"));
        assertTrue(customer.getLastName().equals("ULIANOV"));
    }

    @Test
    public void testGetAllCustomers() {
        List<CustomerDTO> customers = customerDAO.getAllCustomers();
        assertTrue(customers.size()==4);
    }

    @Test
    public void testInsertCustomer(){
        Boolean insertedYn = customerDAO.insertCustomer("FIDEL","CASTRO");
        assertTrue(insertedYn);
        CustomerDTO newCustomer = customerDAO.getCustomerById(5L);
        assertTrue(newCustomer.getFirstName().equals("FIDEL"));
        assertTrue(newCustomer.getLastName().equals("CASTRO"));
    }

    @Test
    public void testUpdateCustomerBonus(){
        Long initialBonus = customerDAO.getCustomerById(1L).getBonus();
        customerDAO.updateBonus(1L,2); // adding 2 points to the current user bonus
        CustomerDTO customer = customerDAO.getCustomerById(1L);
        assertTrue(
                customer.getBonus()==
                (initialBonus.longValue()+2L) //new Bonus should be the initial bonus + 2L
        );
    }
}
