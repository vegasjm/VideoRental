package com.videorental.persistence.dao;

import com.videorental.persistence.model.TransactionDTO;

import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
public interface TransactionDAO {
    List<TransactionDTO> getTransactionsByCustomerId(Long customerId);

    Boolean insertCustomerTransaction(Long customerId, Long movieId, Long nDays, Long nExtraDays, Long price);
}
