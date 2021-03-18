package com.example.AssignmentMovieTicket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

//"com.example.AssignmentMovieTicket.Controller","com.example.AssignmentMovieTicket.Model","com.example.AssignmentMovieTicket.Config"
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.AssignmentMovieTicket"})
public class AssignmentMovieTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentMovieTicketApplication.class, args);
	}

}
