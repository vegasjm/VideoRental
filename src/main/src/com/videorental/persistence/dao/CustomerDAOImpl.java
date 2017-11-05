package com.videorental.persistence.dao;

import com.videorental.persistence.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vegasjm on 01/08/2016.
 */
@Service("customerDAOImpl")
public class CustomerDAOImpl implements CustomerDAO {
    private static Logger logger = Logger.getLogger(CustomerDAOImpl.class.getName());

    @Autowired
    private DataSource dataSource;

    @Value("${SQL.GET.CUSTOMER.BY.ACCOUNTID}")
    private String getCustomerByIdSQL;

    @Value("${SQL.GET.ALL.CUSTOMER}")
    private String getALlCustomersSQL;

    @Value("${SQL.INSERT.CUSTOMER}")
    private String insertCustomerSQL;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        logger.log(Level.INFO, "CustomerDAOImpl - getCustomerById id:" + id);
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(getCustomerByIdSQL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                CustomerDTO customer = new CustomerDTO();
                customer.setId(rs.getLong("ID"));
                customer.setBonus(rs.getLong("BONUS"));
                customer.setFirstName(rs.getString("FIRST_NAME"));
                customer.setLastName(rs.getString("LAST_NAME"));
                connection.close();
                statement.close();
                rs.close();
                return customer;
            }
        }catch(Exception e){
            logger.log(Level.SEVERE, "CustomerDAOImpl - getCustomerById id:"+id+" : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        logger.log(Level.INFO, "CustomerDAOImpl - getAllCustomers ");
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(getALlCustomersSQL);
            ResultSet rs = statement.executeQuery();
            List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
            while(rs.next()){
                CustomerDTO customer = new CustomerDTO();
                customer.setId(rs.getLong("ID"));
                customer.setBonus(rs.getLong("BONUS"));
                customer.setFirstName(rs.getString("FIRST_NAME"));
                customer.setLastName(rs.getString("LAST_NAME"));
                customers.add(customer);
            }
            connection.close();
            statement.close();
            rs.close();
            return customers;
        }catch(Exception e){
            logger.log(Level.SEVERE, "CustomerDAOImpl - getAllCustomers :  "+e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean insertCustomer(String name, String surname) {
        logger.log(Level.INFO, "CustomerDAOImpl - insertCustomer name:"+name+"  /  surname:"+surname+"");
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(insertCustomerSQL);
            statement.setString(1, name);
            statement.setString(2, surname);
            boolean result =statement.executeUpdate()==1;
            connection.close();
            statement.close();
            return result;
        }catch(Exception e){
            logger.log(Level.SEVERE, "CustomerDAOImpl - insertCustomer name:"+name+"  /  surname:"+surname+": "+e.getMessage());
        }
        return false;
    }
}
