package com.teampheonix.tpapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TpApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpApiGatewayApplication.class, args);
	}

}
