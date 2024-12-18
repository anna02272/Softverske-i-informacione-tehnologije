package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@RequestMapping(value="/Locale")
public class LocaleController {
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@PostMapping(value="/ChangeLocale")
	@ResponseBody
	public Map<String, Object> change(HttpSession session, HttpServletRequest request) throws IOException {
		
		Locale lokalizacija = localeResolver.resolveLocale(request);
		System.out.println(lokalizacija);
		
		Map<String, Object> odgovor = new LinkedHashMap<>();
		odgovor.put("status", "ok");
		return odgovor;
	}
}
