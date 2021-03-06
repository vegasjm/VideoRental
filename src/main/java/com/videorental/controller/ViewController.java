package com.videorental.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", "Init Page");
		return "index";
	}

}
