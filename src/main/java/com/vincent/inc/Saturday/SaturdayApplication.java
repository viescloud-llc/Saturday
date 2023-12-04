package com.vincent.inc.Saturday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;
import com.vincent.inc.viesspringutils.Application;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class SaturdayApplication extends Application {

	public static void main(String[] args) {
		SpringApplication.run(SaturdayApplication.class, args);
	}

	@Override
	public String getApplicationName() {
		return "Saturday";
	}
}
