package br.com.jawc.api_clima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiClimaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiClimaApplication.class, args);
	}

}
