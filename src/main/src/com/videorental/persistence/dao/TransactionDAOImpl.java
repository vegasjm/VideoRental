package com.videorental.persistence.dao;

import com.videorental.persistence.model.TransactionDTO;
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
@Service("transactionDAOImpl")
public class TransactionDAOImpl implements TransactionDAO{
    private static Logger logger = Logger.getLogger(TransactionDAOImpl.class.getName());

    @Autowired
    private DataSource dataSource;
    @Value("${SQL.GET.CUSTOMER.TRANSACTIONS.BY.CUSTOMER.ID}")
    private String getTransactionsByCustomerIdSQL;
    @Value("${SQL.INSERT.CUSTOMER.TRANSACTIONS}")
    private String insertCustomerTransactionSQL;

    @Override
    public List<TransactionDTO> getTransactionsByCustomerId(Long customerId) {
        logger.log(Level.INFO, "TransactionDAOImpl - getTransactionsByCustomerId customerId:"+customerId);
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(getTransactionsByCustomerIdSQL);
            statement.setLong(1, customerId);
            ResultSet rs = statement.executeQuery();
            List<TransactionDTO> txs = new ArrayList<TransactionDTO>();
            while(rs.next()){
                TransactionDTO tx = new TransactionDTO();
                tx.setId(rs.getLong("ID"));
                tx.setCustomerId(rs.getLong("CUSTOMER_ID"));
                tx.setIsSettled(rs.getLong("IS_SETTLED"));
                tx.setMovieId(rs.getLong("MOVIE_ID"));
                tx.setnDays(rs.getLong("N_DAYS"));
                tx.setnExtraDays(rs.getLong("N_EXTRA_DAYS"));
                tx.setPrice(rs.getLong("PRICE"));
                txs.add(tx);
            }
            connection.close();
            statement.close();
            rs.close();
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
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(insertCustomerTransactionSQL);
                statement.setLong(1, customerId);
                statement.setLong(2, movieId);
                statement.setLong(3, nDays);
                statement.setLong(4, nExtraDays);
                statement.setLong(5, price);
                boolean result =statement.executeUpdate()==1;
                connection.close();
                statement.close();
                return result;            } catch (Exception e) {
                logger.log(Level.SEVERE, "TransactionDAOImpl - insertCustomerTransaction customerId:"
                        + customerId + "  /  movieId:" + movieId + "  /  nDays:" + nDays + "  /  nExtraDays:" + nExtraDays + "  /  price:" + price + ": "
                        + e.getMessage());
            }
            return false;
        }
}
