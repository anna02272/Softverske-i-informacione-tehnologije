package com.ftn.PrviMavenVebProjekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/Operators")
public class OperatorsController {
	
	
	@GetMapping
	public String index() {
		return "operators";
	}	
}
