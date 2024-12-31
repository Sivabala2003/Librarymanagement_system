package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Books;
import com.example.demo.model.User;
import com.example.demo.service.BooksService;
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
@RequestMapping("api/books")
public class BooksController {
    @Autowired
    BooksService booksService;

    @GetMapping("/bookPaging")
    public Page<Books> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return booksService.getAllBooks(pageable);
    }
    @GetMapping("/allBook")
    public List<Books> getAllBooks(){

        return booksService.getAllBooks();
    }

    @GetMapping("/getBookById/{id}")
    public Books getBookById(@PathVariable Long id){
        return booksService.getBookById(id);
    }
    @PostMapping("/addBooks")
    public ResponseEntity<Books> addBooks(@Valid @RequestBody Books books){
        Books addedBooks =  booksService.addBooks(books);
        return new ResponseEntity<>(addedBooks, HttpStatus.OK);}

    @PutMapping("updateBook/{id}")

    public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books bookDetails) {
        Books updatedBook = booksService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("deleteBook/{id}")

    public String deleteBook(@PathVariable Long id) {
        try {
            booksService.deleteBook(id);
            return "Book deleted successfully with ID: " + id;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
