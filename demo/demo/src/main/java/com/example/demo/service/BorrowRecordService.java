package com.example.demo.service;

import com.example.demo.model.Books;
import com.example.demo.model.BorrowRecord;
import com.example.demo.model.User;
import com.example.demo.repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class BorrowRecordService {
@Autowired
private BorrowRecordRepository borrowRecordRepository;

    public Page<BorrowRecord> getBorrowRecord(Pageable pageable) {
        return borrowRecordRepository.findAll(pageable);
    }

    public List<BorrowRecord> getAllRecord(){
        return borrowRecordRepository.findAll();
    }

    public BorrowRecord getRecordById(Long id){
        return borrowRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("record id" + id + "not found"));
    }

    public BorrowRecord addBorrowRecord(BorrowRecord borrowRecord){
        return borrowRecordRepository.save(borrowRecord);
    }


    public BorrowRecord updateBorrowRecord(Long id, BorrowRecord updatedBorrowRecordDetails) {
        // Find the existing borrow  by ID
        return borrowRecordRepository.findById(id).map(existingBorrowRecord -> {
            existingBorrowRecord.setBorrowDate(updatedBorrowRecordDetails.getBorrowDate());
            existingBorrowRecord.setReturnDate(updatedBorrowRecordDetails.getReturnDate());

            // Save updated entity
            return borrowRecordRepository.save(existingBorrowRecord);
        }).orElseThrow(() -> new IllegalStateException("Book not found with id " + id));
    }

    public void deleteBorrowRecord(Long id) {
        if (borrowRecordRepository.existsById(id)) {
            borrowRecordRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

}
