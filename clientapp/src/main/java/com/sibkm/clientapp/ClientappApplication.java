package com.sibkm.clientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientappApplication.class, args);
		System.out.println("\nClient App is running...");
	}

}