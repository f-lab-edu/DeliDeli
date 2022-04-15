package flab.delideli;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@EnableCaching
@SpringBootApplication
public class DelideliApplication {
	public static void main(String[] args) {
		SpringApplication.run(DelideliApplication.class, args);
	}
}
