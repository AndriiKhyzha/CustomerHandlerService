package com.customer.handling.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class CustomerHandlerServiceApplication {

//	public static final Logger logger = LoggerFactory.getLogger(RestAppApplication.class);

	public static void main(String[] args) throws IOException {

//		try{
//			LogManager.getLogManager().readConfiguration(RestAppApplication.class.getResourceAsStream("/logging properties"));
//		} catch (IOException e){
//			System.err.println("Could not setup log configuration" + e.toString());
//		}
		SpringApplication.run(CustomerHandlerServiceApplication.class, args);
	}
}
