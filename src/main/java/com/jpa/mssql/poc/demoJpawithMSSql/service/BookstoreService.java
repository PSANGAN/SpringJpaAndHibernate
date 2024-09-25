package com.jpa.mssql.poc.demoJpawithMSSql.service;

import com.jpa.mssql.poc.demoJpawithMSSql.dto.AuthorNameAge;
import com.jpa.mssql.poc.demoJpawithMSSql.entity.Author;
import com.jpa.mssql.poc.demoJpawithMSSql.entity.Book;
import com.jpa.mssql.poc.demoJpawithMSSql.repository.AuthorRepository;
import com.jpa.mssql.poc.demoJpawithMSSql.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void insertAuthorWithBooks() {

        Author jn = new Author();
        jn.setName("Joana Nimar");
        jn.setAge(34);
        jn.setGenre("History");

        Book jn01 = new Book();
        jn01.setIsbn("001-JN");
        jn01.setTitle("A History of Ancient Prague");

        Book jn02 = new Book();
        jn02.setIsbn("002-JN");
        jn02.setTitle("A People's History");

        Book jn03 = new Book();
        jn03.setIsbn("003-JN");
        jn03.setTitle("World History");

        jn.addBook(jn01);
        jn.addBook(jn02);
        jn.addBook(jn03);

        authorRepository.save(jn);
    }

    public List<AuthorNameAge> fetchFirst2ByBirthplace() {

        return authorRepository.findFirst2ByGenre("History");
    }
}
