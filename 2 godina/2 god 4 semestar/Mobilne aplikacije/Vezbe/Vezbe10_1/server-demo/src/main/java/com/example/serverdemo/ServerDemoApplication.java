package com.example.serverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Kada se pokrene aplikacija, moguce joj je pristupiti na putanji
 * http://localhost:8081/index.html
 */

@SpringBootApplication
public class ServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerDemoApplication.class, args);
	}

}
