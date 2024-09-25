package com.jpa.mssql.poc.demoJpawithMSSql;

import com.jpa.mssql.poc.demoJpawithMSSql.dto.AuthorNameAge;
import com.jpa.mssql.poc.demoJpawithMSSql.entity.Item;
import com.jpa.mssql.poc.demoJpawithMSSql.service.BookstoreService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
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
			EntityManagerFactory emf =
					Persistence.createEntityManagerFactory("ch05.mapping");
			EntityManager em = emf.createEntityManager();

			try {
				em.getTransaction().begin();

				Item item = new Item();
				item.setName("Some Item");
				item.setAuctionEnd(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));

				em.persist(item);

				em.getTransaction().commit();
				em.getTransaction().begin();

				List<Item> items =
						em.createQuery("select i from Item i", Item.class).getResultList();
				//SELECT * from ITEM

				em.getTransaction().commit();



			} finally {
				em.close();
				emf.close();
			}

		};
	}
}
