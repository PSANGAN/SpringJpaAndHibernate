package com.jpa.mssql.poc.demoJpawithMSSql.repository;

import com.jpa.mssql.poc.demoJpawithMSSql.dto.AuthorNameAge;
import com.jpa.mssql.poc.demoJpawithMSSql.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    List<AuthorNameAge> findFirst2ByGenre(String genre);
}