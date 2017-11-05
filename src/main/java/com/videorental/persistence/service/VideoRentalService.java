package com.videorental.persistence.service;

import com.videorental.persistence.model.MovieDTO;
import com.videorental.persistence.webModel.TransactionModel;

import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
public interface VideoRentalService {

    List<TransactionModel> getTransactionsByCustomerId(Long customerId);

    List<MovieDTO> getAllMovies();

    Boolean insertCustomerTransaction(Long customerId, Long movieId, Long nDays, Long nExtraDays);
}
