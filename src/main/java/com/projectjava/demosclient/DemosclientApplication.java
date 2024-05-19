package com.projectjava.demosclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;





@SpringBootApplication
@EnableEncryptableProperties
public class DemosclientApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemosclientApplication.class, args);
	}

}
