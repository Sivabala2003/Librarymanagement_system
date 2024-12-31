package com.example.demo.repository;


import com.example.demo.model.Books;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public  interface BooksRepository extends JpaRepository<Books, Long>{

    }

