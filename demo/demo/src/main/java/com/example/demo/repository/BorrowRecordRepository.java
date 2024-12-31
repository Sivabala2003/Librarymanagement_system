package com.example.demo.repository;

import com.example.demo.model.Books;
import com.example.demo.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
}
