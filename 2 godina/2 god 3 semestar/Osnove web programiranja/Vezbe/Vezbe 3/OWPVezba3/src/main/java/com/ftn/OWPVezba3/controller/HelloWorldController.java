package com.ftn.OWPVezba3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("HelloWorld")
public class HelloWorldController {
	
	@GetMapping()
	public String helloWorld() {
		return "/helloWorld.html";
	}

}
