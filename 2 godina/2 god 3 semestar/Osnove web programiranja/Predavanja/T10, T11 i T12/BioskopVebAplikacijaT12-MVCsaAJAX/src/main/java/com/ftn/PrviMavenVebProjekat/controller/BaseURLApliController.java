package com.ftn.PrviMavenVebProjekat.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/baseURL")
public class BaseURLApliController {

	@Autowired
	private ServletContext servletContext;
	private String baseURL;

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}

	@GetMapping
	public Map<String, Object> baseURL() {
		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		odgovor.put("baseURL", baseURL);	
		return odgovor;
	}
}
