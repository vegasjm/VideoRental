package com.videorental.service;

import com.videorental.model.MovieDTO;
import com.videorental.webModel.TransactionModel;

import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
public interface VideoRentalService {

    List<TransactionModel> getTransactionsByCustomerId(Long customerId);

    List<MovieDTO> getAllMovies();

    Boolean insertCustomerTransaction(Long customerId, Long movieId, Long nDays, Long nExtraDays);
}
