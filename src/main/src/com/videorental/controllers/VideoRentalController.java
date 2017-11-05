package com.videorental.controllers;

import com.videorental.persistence.model.MovieDTO;
import com.videorental.persistence.service.VideoRentalService;
import com.videorental.persistence.webModel.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * Created by vegasjm on 01/08/2016.
 */
@Controller
@RequestMapping("management")
public class VideoRentalController {

    @Autowired
    private VideoRentalService videoRentalService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView getDetailsPage() {
        String message = "Hello World!";
        return new ModelAndView("welcome", "message", message);
    }

    @RequestMapping(value = "/getCustomerTransactions", method = RequestMethod.GET,  produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
         List<TransactionModel> getCustomer(@RequestParam Long customerId) throws IOException {
        return videoRentalService.getTransactionsByCustomerId(customerId);
    }

    @RequestMapping(value = "/getAllMovies", method = RequestMethod.GET,  produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<MovieDTO> getAllMovies() throws IOException {
        return videoRentalService.getAllMovies();
    }

    @RequestMapping(value = "/insertCustomerTransaction", method = RequestMethod.POST,  produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Boolean insertCustomerTransaction(@RequestParam("customerId") Long customerId, @RequestParam("movieId") Long movieId, @RequestParam("nDays") Long nDays, @RequestParam("nExtraDays") Long nExtraDays) throws IOException {
        return videoRentalService.insertCustomerTransaction(customerId,movieId, nDays, nExtraDays);
    }

}
