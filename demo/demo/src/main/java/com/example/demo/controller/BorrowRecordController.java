package com.example.demo.controller;
import com.example.demo.model.BorrowRecord;
import com.example.demo.service.BorrowRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/borrow")
public class BorrowRecordController {

    @Autowired
    BorrowRecordService borrowRecordService;

    @GetMapping("/borrowPaging")
    public Page<BorrowRecord> getRecord(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return borrowRecordService.getBorrowRecord(pageable);
    }
    @GetMapping("/allRecord")
    public List<BorrowRecord> getAllRecord(){

        return borrowRecordService.getAllRecord();
    }

    @GetMapping("/getRecordById/{id}")
    public BorrowRecord getRecordById(@PathVariable Long id){
        return borrowRecordService.getRecordById(id);
    }

    @PostMapping("/addRecord")
    public ResponseEntity<BorrowRecord> addBorrowRecord(@Valid @RequestBody BorrowRecord borrowRecord){
        BorrowRecord addedBorrowRecord =  borrowRecordService.addBorrowRecord(borrowRecord);
        return new ResponseEntity<>(addedBorrowRecord, HttpStatus.OK);
    }


    @PutMapping("updateRecord/{id}")

    public ResponseEntity<BorrowRecord> updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecord borrowRecordDetails) {
        BorrowRecord updatedBorrowRecord = borrowRecordService.updateBorrowRecord(id, borrowRecordDetails);
        return ResponseEntity.ok(updatedBorrowRecord);
    }

    @DeleteMapping("deleteRecord/{id}")

    public String deleteBorrowRecord(@PathVariable Long id) {
        try {
            borrowRecordService.deleteBorrowRecord(id);
            return "record deleted successfully with ID: " + id;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
