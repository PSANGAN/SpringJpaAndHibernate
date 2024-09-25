package com.jpa.mssql.poc.demoJpawithMSSql;

import com.jpa.mssql.poc.demoJpawithMSSql.dto.AuthorNameAge;
import com.jpa.mssql.poc.demoJpawithMSSql.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoJpawithMsSqlApplication {

	private final BookstoreService bookstoreService;

	public DemoJpawithMsSqlApplication(BookstoreService bookstoreService) {
		this.bookstoreService = bookstoreService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoJpawithMsSqlApplication.class, args);
	}

	@Bean
	public ApplicationRunner init() {
		return args -> {
			bookstoreService.insertAuthorWithBooks();

			List<AuthorNameAge> authors = bookstoreService.fetchFirst2ByBirthplace();

			System.out.println("Number of authors:" + authors.size());

			for (AuthorNameAge author : authors) {
				System.out.println("Author name: " + author.getName()
						+ " | Age: " + author.getAge());
			}

		};
	}
}
