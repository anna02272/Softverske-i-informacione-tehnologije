package com.ftn.PrviMavenVebProjekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/TestAjaxHTML")
public class TestAjaxHTMLController {

	
	@GetMapping
	public String index(@RequestParam(required = false) String tekst) {
		System.out.println(tekst);
		// prosleđivanje
		return "testAjaxHTML";
	}
	
	@PostMapping
	public String indexPost(@RequestParam(required = false) String tekst) {
		System.out.println(tekst);
		// prosleđivanje
		return "testAjaxHTML";
	}
}
