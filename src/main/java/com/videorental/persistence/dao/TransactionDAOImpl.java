package com.videorental.persistence.dao;

import com.videorental.persistence.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vegasjm on 05/11/2017.
 */
@Service("transactionDAOImpl")
public class TransactionDAOImpl implements TransactionDAO{
    private static Logger logger = Logger.getLogger(TransactionDAOImpl.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${SQL.GET.CUSTOMER.TRANSACTIONS.BY.CUSTOMER.ID}")
    private String getTransactionsByCustomerIdSQL;
    @Value("${SQL.INSERT.CUSTOMER.TRANSACTIONS}")
    private String insertCustomerTransactionSQL;

    @Override
    public List<TransactionDTO> getTransactionsByCustomerId(Long customerId) {
        logger.log(Level.INFO, "TransactionDAOImpl - getTransactionsByCustomerId customerId:"+customerId);
        try {
            Object[] parameters = new Object[] {customerId};
            List<TransactionDTO> txs = jdbcTemplate.query(getTransactionsByCustomerIdSQL, parameters, new BeanPropertyRowMapper<TransactionDTO>(TransactionDTO.class));
            return txs;
        }catch(Exception e){
            logger.log(Level.SEVERE, "TransactionDAOImpl - getTransactionsByCustomerId customerId:"+customerId+" : "+e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean insertCustomerTransaction(Long customerId,Long movieId,Long nDays,Long nExtraDays,Long price) {
            logger.log(Level.INFO, "TransactionDAOImpl - insertCustomerTransaction customerId:"
                    + customerId + "  /  movieId:" + movieId + "  /  nDays:" + nDays + "  /  nExtraDays:" + nExtraDays + "  /  price:" + price + "");
            try {
                Object[] parameters = new Object[] {customerId,movieId,nDays,nExtraDays,price};
                boolean result = jdbcTemplate.update(insertCustomerTransactionSQL,parameters)==1;
                return result;
            } catch (Exception e) {
                logger.log(Level.SEVERE, "TransactionDAOImpl - insertCustomerTransaction customerId:"
                        + customerId + "  /  movieId:" + movieId + "  /  nDays:" + nDays + "  /  nExtraDays:" + nExtraDays + "  /  price:" + price + ": "
                        + e.getMessage());
            }
            return false;
        }
}
