package com.jpa.mssql.poc.demoJpawithMSSql;

import com.jpa.mssql.poc.demoJpawithMSSql.dto.AuthorNameAge;
import com.jpa.mssql.poc.demoJpawithMSSql.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUtil;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoJpawithMsSqlApplication {


	public DemoJpawithMsSqlApplication() {

	}

	public static void main(String[] args) {
		SpringApplication.run(DemoJpawithMsSqlApplication.class, args);
	}

// void doWork(){
//	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("MSSQL");
//	 EntityManager em = emf.createEntityManager();
//	 em.getTransaction().begin();
//	 Item item = em.find(Item.class, 1L);
//	 System.out.println(em.contains(item)); // True bcs, the entity is attached
//	 em.detach(item);
//	 System.out.println(em.contains(item)); // False bcs, the entity is detached
//	 em.getTransaction().commit();
//	 em.close();
// }

	void doWorkWithHibernate01(){
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MSSQL");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//
//		Item detachedItem = new Item();
//		detachedItem.setName("Joe");
//		em.persist(detachedItem);
//		em.getTransaction().commit();
//		em.close();
//		Long itemId = detachedItem.getId();
//
//		detachedItem.setName("John");
//
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
//
//		System.out.printf("Is Managed by Persistence = %b\n", em.contains(detachedItem) ); // False
//		Item mergedItem = em.merge(detachedItem);
//		System.out.printf("Is Managed by Persistence = %b\n", em.contains(detachedItem) ); // False
//		System.out.printf("Is Managed by Persistence = %b\n", em.contains(mergedItem) ); // True
//		mergedItem.setName("Jack");
//
//		em.getTransaction().commit(); // UPDATE in database
//		em.close();
//
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
//		Item item = em.find(Item.class, itemId);
//		System.out.println(item.getName());
//		System.out.println(item.getName().equals("Jack")); // true
//		em.getTransaction().commit();
//		em.close();
	}

	private List<Item> storeTestData() {
		List<Item> itemList = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MSSQL");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Long[] categoryIds = new Long[3];
		Long[] itemIds = new Long[3];
		Long[] userIds = new Long[3];

		User johndoe = new User("johndoe");
		em.persist(johndoe);
		userIds[0] = johndoe.getId();

		User janeroe = new User("janeroe");
		em.persist(janeroe);
		userIds[1] = janeroe.getId();

		User robertdoe = new User("robertdoe");
		em.persist(robertdoe);
		userIds[2] = robertdoe.getId();

		Category category = new Category("Category One");
		em.persist(category);
		categoryIds[0] = category.getId();

		Item item = new Item("Item One", LocalDate.now().plusDays(1), johndoe);
		em.persist(item);
		itemIds[0] = item.getId();
		category.addItem(item);
		item.addCategory(category);
		for (int i = 1; i <= 3; i++) {
			Bid bid = new Bid(item, robertdoe, new BigDecimal(9 + i));
			item.addBid(bid);
			em.persist(bid);
		}

		category = new Category("Category Two");
		em.persist(category);
		categoryIds[1] = category.getId();
		itemList.add(item);
		item = new Item("Item Two", LocalDate.now().plusDays(1), johndoe);
		em.persist(item);
		itemIds[1] = item.getId();
		category.addItem(item);
		item.addCategory(category);
		for (int i = 1; i <= 1; i++) {
			Bid bid = new Bid(item, janeroe, new BigDecimal(2 + i));
			item.addBid(bid);
			em.persist(bid);
		}
		itemList.add(item);
		item = new Item("Item Three", LocalDate.now().plusDays(2), janeroe);
		em.persist(item);
		itemIds[2] = item.getId();
		category.addItem(item);
		item.addCategory(category);

		category = new Category("Category Three");
		em.persist(category);
		categoryIds[2] = category.getId();
		itemList.add(item);
		em.getTransaction().commit();
		em.close();

		return itemList;
	}
	@Bean
	public ApplicationRunner init() {

		return args -> {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("MSSQL");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			Item refItem = em.getReference(Item.class, 1L);
			PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();

			System.out.println(refItem.getId().toString()); // No DB hit
			System.out.println(persistenceUtil.isLoaded(refItem)); // False

			System.out.println(refItem.getName()); // BD Hit
			System.out.println(persistenceUtil.isLoaded(refItem)); // True

			em.getTransaction().commit();
			em.close();

		};
	}
}
