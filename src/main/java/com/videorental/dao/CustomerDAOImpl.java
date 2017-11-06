package com.videorental.dao;

import com.videorental.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vegasjm on 05/11/2017.
 */
@Service("customerDAOImpl")
public class CustomerDAOImpl implements CustomerDAO {
    private static Logger logger = Logger.getLogger(CustomerDAOImpl.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${SQL.GET.CUSTOMER.BY.ACCOUNTID}")
    private String getCustomerByIdSQL;

    @Value("${SQL.GET.ALL.CUSTOMER}")
    private String getALlCustomersSQL;

    @Value("${SQL.INSERT.CUSTOMER}")
    private String insertCustomerSQL;

    @Value("${SQL.UPDATE.CUSTOMER.BONUS}")
    private String updateCustomerBonusSQL;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        logger.log(Level.INFO, "CustomerDAOImpl - getCustomerById id:" + id);
        try {
            Object[] parameters = new Object[] {new Long(id)};
            CustomerDTO customer = jdbcTemplate.queryForObject(getCustomerByIdSQL, parameters, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
            logger.log(Level.INFO, "CustomerDTO:"+customer.toString());
            return customer;
        }catch(Exception e){
            logger.log(Level.SEVERE, "CustomerDAOImpl - getCustomerById id:"+id+" : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        logger.log(Level.INFO, "CustomerDAOImpl - getAllCustomers ");
        try {
            Object[] parameters = new Object[] {};
            List<CustomerDTO> customers = jdbcTemplate.query(getALlCustomersSQL, parameters, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
            return customers;
        }catch(Exception e){
            logger.log(Level.SEVERE, "CustomerDAOImpl - getAllCustomers :  "+e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean insertCustomer(String name, String surname) {
        logger.log(Level.INFO, "CustomerDAOImpl - insertCustomer name:"+name+"  /  surname:"+surname+"");
        try {
            Object[] parameters = new Object[] {name,surname};
            boolean result = jdbcTemplate.update(insertCustomerSQL,parameters)==1;
            return result;
        }catch(Exception e){
            logger.log(Level.SEVERE, "CustomerDAOImpl - insertCustomer name:"+name+"  /  surname:"+surname+": "+e.getMessage());
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updateBonus(Long customerId, int bonus) {
        logger.log(Level.INFO, "CustomerDAOImpl - updateBonus id:"+customerId+"  /  bonus:"+bonus+"");
        try {
            Object[] parameters = new Object[] {bonus,customerId};
            boolean result = jdbcTemplate.update(updateCustomerBonusSQL,parameters)==1;
            return result;
        }catch(Exception e){
            logger.log(Level.SEVERE, "CustomerDAOImpl - updateBonus id:"+customerId+"  /  bonus:"+bonus+": "+e.getMessage());
        }
        return false;
    }
}
