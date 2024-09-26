package com.jpa.mssql.poc.demoJpawithMSSql;

import com.jpa.mssql.poc.demoJpawithMSSql.dto.AuthorNameAge;
import com.jpa.mssql.poc.demoJpawithMSSql.entity.Item;
import com.jpa.mssql.poc.demoJpawithMSSql.entity.User;
import com.jpa.mssql.poc.demoJpawithMSSql.repository.UserRepository;
import com.jpa.mssql.poc.demoJpawithMSSql.service.BookstoreService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoJpawithMsSqlApplication {

	private final BookstoreService bookstoreService;

	public DemoJpawithMsSqlApplication(BookstoreService bookstoreService) {
		this.bookstoreService = bookstoreService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoJpawithMsSqlApplication.class, args);
	}

	void doWork(){
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
	}

	void doWork(UserRepository userRepository){
		User user1 = new User("beth", LocalDate.of(2020, Month.AUGUST, 3));
		User user2 = new User("mike", LocalDate.of(2020, Month.JANUARY, 18));
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.findAll().forEach(System.out::println);
	}

	private static List<User> generateUsers() {
		List<User> users = new ArrayList<>();

		User john = new User("john", LocalDate.of(2020, Month.APRIL, 13));
		john.setEmail("john@somedomain.com");
		john.setLevel(1);
		john.setActive(true);

		User mike = new User("mike", LocalDate.of(2020, Month.JANUARY, 18));
		mike.setEmail("mike@somedomain.com");
		mike.setLevel(3);
		mike.setActive(true);

		User james = new User("james", LocalDate.of(2020, Month.MARCH, 11));
		james.setEmail("james@someotherdomain.com");
		james.setLevel(3);
		james.setActive(false);

		User katie = new User("katie", LocalDate.of(2021, Month.JANUARY, 5));
		katie.setEmail("katie@somedomain.com");
		katie.setLevel(5);
		katie.setActive(true);

		User beth = new User("beth", LocalDate.of(2020, Month.AUGUST, 3));
		beth.setEmail("beth@somedomain.com");
		beth.setLevel(2);
		beth.setActive(true);

		User julius = new User("julius", LocalDate.of(2021, Month.FEBRUARY, 9));
		julius.setEmail("julius@someotherdomain.com");
		julius.setLevel(4);
		julius.setActive(true);

		User darren = new User("darren", LocalDate.of(2020, Month.DECEMBER, 11));
		darren.setEmail("darren@somedomain.com");
		darren.setLevel(2);
		darren.setActive(true);

		User marion = new User("marion", LocalDate.of(2020, Month.SEPTEMBER, 23));
		marion.setEmail("marion@someotherdomain.com");
		marion.setLevel(2);
		marion.setActive(false);

		User stephanie = new User("stephanie", LocalDate.of(2020, Month.JANUARY, 18));
		stephanie.setEmail("stephanie@someotherdomain.com");
		stephanie.setLevel(4);
		stephanie.setActive(true);

		User burk = new User("burk", LocalDate.of(2020, Month.NOVEMBER, 28));
		burk.setEmail("burk@somedomain.com");
		burk.setLevel(1);
		burk.setActive(true);

		users.add(john);
		users.add(mike);
		users.add(james);
		users.add(katie);
		users.add(beth);
		users.add(julius);
		users.add(darren);
		users.add(marion);
		users.add(stephanie);
		users.add(burk);

		return users;
	}

	@Bean
	public ApplicationRunner init(UserRepository userRepository) {

		return args -> {
			// userRepository.saveAll(generateUsers());

			/*
			Iterable<User> users =  userRepository.findAll();
			users.iterator().forEachRemaining( System.out::println);
			 */

//			System.out.println(userRepository.findByUsername("beth").getEmail());

//			List<User> orderedUsersList =  userRepository.findByOrderByUsernameAsc();
//			orderedUsersList.forEach(System.out::println);

//			List<User> usersBetweenRegistrationDate = userRepository.findByRegistrationDateBetween(
//					LocalDate.of(2020, Month.JULY, 1),
//					LocalDate.of(2020, Month.DECEMBER, 31));
//			usersBetweenRegistrationDate.forEach(System.out::println);

//			User user1 = userRepository.findFirstByOrderByUsernameAsc();
//			System.out.println(user1.toString());
//			User user2 = userRepository.findTopByOrderByRegistrationDateDesc();
//			System.out.println(user2.toString());

//			// Total no of rows - 10, page no starts as 0 and its fetch first 3 records
//			Page<User> userPage = userRepository.findAll(PageRequest.of(0, 3));
//			userPage.forEach(System.out::println);
//			//  its fetch 4,5,6 records
//			userPage = userRepository.findAll(PageRequest.of(1, 3));
//			userPage.forEach(System.out::println);
//			//  its fetch 7,8,9 records
//			userPage = userRepository.findAll(PageRequest.of(2, 3));
//			userPage.forEach(System.out::println);
//			//  its fetch 10 records
//			userPage = userRepository.findAll(PageRequest.of(3, 3));
//			userPage.forEach(System.out::println);

//			// Find the first two users with level 2, ordered by registration date
//			List<User> users = userRepository.findFirst2ByLevel(2,Sort.by("registrationDate"));
//			users.forEach(System.out::println);

			try(Stream<User> result =
						userRepository.findByEmailContaining("someother")
								.and(userRepository.findByLevel(2))
								.stream().distinct()) {
				System.out.println(result.count());
			}









			// userRepository.deleteAll();

		};
	}
}
