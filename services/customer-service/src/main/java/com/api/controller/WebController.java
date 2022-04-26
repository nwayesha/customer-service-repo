package com.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody String testPage() {
		return "Hello, World";

	}
}