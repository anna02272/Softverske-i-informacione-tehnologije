package rs.ac.uns.ftn.informatika.osa.spring.pr25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class PurchaseApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PurchaseApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PurchaseApplication.class, args);
	}  
}

