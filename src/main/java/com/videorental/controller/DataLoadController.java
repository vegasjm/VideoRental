package com.videorental.controller;

import com.videorental.persistence.service.DataLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by vegasjm on 05/11/2017.
 */
@Controller
public class DataLoadController {

    @Autowired
    private DataLoadService dataLoadService;

    @RequestMapping(value = "/initDataLoad", method = RequestMethod.GET,  produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Boolean initDataLoad() throws IOException {
        return dataLoadService.initDataLoad();
    }
}
