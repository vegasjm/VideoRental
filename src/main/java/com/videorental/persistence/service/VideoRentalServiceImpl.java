package com.videorental.persistence.service;

import com.videorental.persistence.dao.CustomerDAO;
import com.videorental.persistence.dao.MovieDAO;
import com.videorental.persistence.dao.MovieTypeDAO;
import com.videorental.persistence.dao.TransactionDAO;
import com.videorental.persistence.model.CustomerDTO;
import com.videorental.persistence.model.MovieDTO;
import com.videorental.persistence.model.MovieTypeDTO;
import com.videorental.persistence.model.TransactionDTO;
import com.videorental.persistence.webModel.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
@Component
public class VideoRentalServiceImpl implements VideoRentalService{

    @Autowired
    @Qualifier("customerDAOImpl")
    private CustomerDAO customerDAO;

    @Autowired
    @Qualifier("transactionDAOImpl")
    private TransactionDAO transactionDAO;

    @Autowired
    @Qualifier("movieDAOImpl")
    private MovieDAO movieDAO;

    @Autowired
    @Qualifier("movieTypeDAOImpl")
    private MovieTypeDAO movieTypeDAO;

    private static final long PREMIUM_PRICE = 40;
    private static final long BASIC_PRICE = 30;

    private static final String NEW_RELEASE ="New release";
    private static final String REGULAR_FILM ="Regular Film";
    private static final String OLD_FILM ="Old Film";
    @Override
    public List<TransactionModel> getTransactionsByCustomerId(Long customerId) {
        List<TransactionDTO> txs =  transactionDAO.getTransactionsByCustomerId(customerId);
        List<TransactionModel> txs_web = new ArrayList<TransactionModel>();
        for(TransactionDTO tx : txs){

            MovieDTO movie = movieDAO.getMovieById(tx.getMovieId());
            Long movieTypeId = movie.getMovieTypeId();
            MovieTypeDTO mtype = movieTypeDAO.getMovieTypeById(movieTypeId);


            TransactionModel tx_web = new TransactionModel();
            tx_web.setnExtraDays(tx.getnExtraDays());
            tx_web.setId(tx.getId());
            tx_web.setnDays(tx.getnDays());
            tx_web.setPrice(tx.getPrice());
            if(tx.getIsSettled()==null)
                tx_web.setIsSettled(false);
            else
            tx_web.setIsSettled(tx.getIsSettled() == 1);
            CustomerDTO c = customerDAO.getCustomerById(tx.getCustomerId());
            tx_web.setCustomer(c.getFirstName()+" "+c.getLastName());
            tx_web.setMovie(movieDAO.getMovieById(tx.getMovieId()).getTitle()+" ("+mtype.getDescription()+")");
            //CALCULATE PRICE TYPES

            Long priceInit = 0l;
            if(mtype!=null && mtype.getDescription().equals(NEW_RELEASE)){
                priceInit = tx.getnDays().longValue()*PREMIUM_PRICE;
            }
            if(mtype!=null && mtype.getDescription().equals(REGULAR_FILM)){
                if(tx.getnDays().longValue()<=3){
                    priceInit = tx.getnDays().longValue()*PREMIUM_PRICE;
                }else{
                    priceInit = (3*PREMIUM_PRICE)  +  (((tx.getnDays().longValue())-3)*BASIC_PRICE);
                }
            }
            if(mtype!=null && mtype.getDescription().equals(OLD_FILM)){
                if(tx.getnDays().longValue()<=5){
                    priceInit = tx.getnDays().longValue()*PREMIUM_PRICE;
                }else{
                    priceInit = (5*PREMIUM_PRICE)  +  (((tx.getnDays().longValue())-5)*BASIC_PRICE);
                }
            }
            tx_web.setPriceInTime(priceInit);

            Long priceExtra = 0l;

            if(mtype!=null && mtype.getDescription().equals(NEW_RELEASE)){
                priceExtra = tx.getnExtraDays().longValue()*PREMIUM_PRICE;
            }
            if(mtype!=null && mtype.getDescription().equals(REGULAR_FILM)){
                if(tx.getnDays().longValue()<3) {
                    if (tx.getnExtraDays().longValue() <= (3-tx.getnDays())) {
                        priceExtra = tx.getnExtraDays().longValue() * PREMIUM_PRICE;
                    } else {
                        priceExtra = ((3-tx.getnDays()) * PREMIUM_PRICE) + (((tx.getnExtraDays().longValue()) - (3-tx.getnDays())) * BASIC_PRICE);
                    }
                }else{
                    priceExtra = (((tx.getnExtraDays().longValue())) * BASIC_PRICE);
                }
            }
            if(mtype!=null && mtype.getDescription().equals(OLD_FILM)){
                if(tx.getnDays().longValue()<5) {
                    if (tx.getnExtraDays().longValue() <= (5-tx.getnDays())) {
                        priceExtra = tx.getnExtraDays().longValue() * PREMIUM_PRICE;
                    } else {
                        priceExtra = ((5-tx.getnDays()) * PREMIUM_PRICE) + (((tx.getnExtraDays().longValue()) - (5-tx.getnDays())) * BASIC_PRICE);
                    }
                }else{
                    priceExtra = (((tx.getnExtraDays().longValue())) * BASIC_PRICE);
                }
            }
            tx_web.setPriceInExtraTime(priceExtra);
            //**********************
            txs_web.add(tx_web);
        }
        return txs_web;
    }

    @Override
    public List<MovieDTO> getAllMovies(){
        return movieDAO.getAllMovies();
    }

    @Override
    public Boolean insertCustomerTransaction(Long customerId, Long movieId, Long nDays, Long nExtraDays) {
        MovieDTO movie = movieDAO.getMovieById(movieId);
        Long movieTypeId = movie.getMovieTypeId();
        MovieTypeDTO mtype = movieTypeDAO.getMovieTypeById(movieTypeId);
        Long price = 0l;
        if(mtype!=null && mtype.getDescription().equals(NEW_RELEASE)){
           price = (nDays.longValue()+nExtraDays.longValue())*PREMIUM_PRICE;
        }
        if(mtype!=null && mtype.getDescription().equals(REGULAR_FILM)){
          if((nDays.longValue()+nExtraDays.longValue())<=3){
              price = (nDays.longValue()+nExtraDays.longValue())*PREMIUM_PRICE;
          }else{
              price = (3*PREMIUM_PRICE)  +  (((nDays.longValue()+nExtraDays.longValue())-3)*BASIC_PRICE);
          }
        }
        if(mtype!=null && mtype.getDescription().equals(OLD_FILM)){
            if((nDays.longValue()+nExtraDays.longValue())<=5){
                price = (nDays.longValue()+nExtraDays.longValue())*PREMIUM_PRICE;
            }else{
                price = (5*PREMIUM_PRICE)  +  (((nDays.longValue()+nExtraDays.longValue())-5)*BASIC_PRICE);
            }
        }
        return transactionDAO.insertCustomerTransaction(customerId,movieId,nDays,nExtraDays,price);
    }
}
