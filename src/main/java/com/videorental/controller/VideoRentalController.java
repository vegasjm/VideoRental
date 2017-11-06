package com.videorental.controller;

import com.videorental.model.MovieDTO;
import com.videorental.service.VideoRentalService;
import com.videorental.webModel.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
@RestController
@RequestMapping("api/management")
public class VideoRentalController {

    @Autowired
    private VideoRentalService videoRentalService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public @ResponseBody String getDetailsPage() {
        String message = "Hello World!";
        return message;
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

    @RequestMapping(value = "/priceSimulation", method = RequestMethod.POST,  produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Long priceSimulation(@RequestParam("movieId") Long movieId, @RequestParam("nDays") Long nDays, @RequestParam("nExtraDays") Long nExtraDays) throws IOException {
        return videoRentalService.priceSimulation(movieId, nDays, nExtraDays);
    }

}
