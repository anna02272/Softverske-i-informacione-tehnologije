package rs.ac.uns.ftn.informatika.osa.spring.pr15;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfiguration {
	
	@Bean
	@Primary
	public DataSource getDataSource(){
		return new CustomDataSource();
	}

}
