package com.bytewheels.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytewheels.app.services.ByteWheelsServiceIF;

@Controller
public class ByteWheelsController {

	@Autowired
	private ByteWheelsServiceIF byteWheelsService;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public String printWelcome() {
		return byteWheelsService.process();
	}
	
}
