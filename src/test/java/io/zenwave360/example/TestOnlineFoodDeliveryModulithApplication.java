package io.zenwave360.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestOnlineFoodDeliveryModulithApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestOnlineFoodDeliveryModulithApplication.class).run(args);
	}

}
