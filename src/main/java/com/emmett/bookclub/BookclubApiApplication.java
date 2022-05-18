package com.emmett.bookclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@SpringBootApplication
public class BookclubApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookclubApiApplication.class, args);
	}

}
