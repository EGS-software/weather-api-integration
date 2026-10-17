package br.com.jawc.api_clima;

import org.springframework.boot.SpringApplication;

public class TestApiClimaApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiClimaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
