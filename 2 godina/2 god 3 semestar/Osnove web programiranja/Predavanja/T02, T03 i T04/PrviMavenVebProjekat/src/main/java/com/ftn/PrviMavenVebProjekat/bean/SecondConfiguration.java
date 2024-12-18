package com.ftn.PrviMavenVebProjekat.bean;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondConfiguration {

	@Bean(name= {"memorijaAplikacije"}, 
			initMethod="init", destroyMethod="destroy")
	public MemorijaAplikacije getMemorijaAplikacije() {
		return new MemorijaAplikacije();
	}
	
	public class MemorijaAplikacije extends HashMap {
		
		@Override
		public String toString() {
			return "MemorijaAplikacije"+this.hashCode();
		}
		
		public void init() {
			//inicijalizacija
			System.out.println("init method called");
		}
		
		public void destroy() {
			//brisanje
			System.out.println("destroy method called");
		}
	}
}
