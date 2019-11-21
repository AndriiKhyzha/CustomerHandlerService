package com.programwithAndrii.restservice.RestApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.logging.LogManager;

@SpringBootApplication
public class RestApplication {

//	public static final Logger logger = LoggerFactory.getLogger(RestAppApplication.class);

	public static void main(String[] args) throws IOException {
//		try{
//			LogManager.getLogManager().readConfiguration(RestAppApplication.class.getResourceAsStream("/logging properties"));
//		} catch (IOException e){
//			System.err.println("Could not setup log configuration" + e.toString());
//		}
		SpringApplication.run(RestApplication.class, args);
	}
}
