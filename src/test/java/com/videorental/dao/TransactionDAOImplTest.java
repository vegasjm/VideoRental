package com.videorental.dao;

import com.videorental.model.MovieDTO;
import com.videorental.model.TransactionDTO;
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
public class TransactionDAOImplTest {

    @Autowired
    TransactionDAOImpl transactionDAO;

    @Autowired
    MovieDAOImpl movieDAO;

    @Test
    public void testGetTransactionsByCustomerId(){
        List<TransactionDTO> transactions = transactionDAO.getTransactionsByCustomerId(1L);
        assertNotNull(transactions);
        assertTrue(transactions.size()==2L);
        TransactionDTO txs0 = transactions.get(0);
        TransactionDTO txs1 = transactions.get(1);
        MovieDTO movie0= movieDAO.getMovieById(txs0.getMovieId());
        MovieDTO movie1= movieDAO.getMovieById(txs1.getMovieId());
        assertTrue(txs0.getPrice()==40L && movie0.getTitle().equals("The Godfather"));
        assertTrue(txs1.getPrice()==240L && movie1.getTitle().equals("The Lord of The Rings"));
    }

    @Test
    public void testInsertCustomerTransaction(){
        Boolean insertedYn = transactionDAO.insertCustomerTransaction(1L,4L,5L,0L,150L);
        assertTrue(insertedYn);
        List<TransactionDTO> transactions = transactionDAO.getTransactionsByCustomerId(1L);
        assertNotNull(transactions);
        assertTrue(transactions.size()==3);
        TransactionDTO txs2 = transactions.get(2);
        MovieDTO movie2= movieDAO.getMovieById(txs2.getMovieId());
        assertTrue( movie2.getTitle().equals("Pulp Fiction"));
    }
}
