package com.jpa.mssql.poc.demoJpawithMSSql.repository;

import com.jpa.mssql.poc.demoJpawithMSSql.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
