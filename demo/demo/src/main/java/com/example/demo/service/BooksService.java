package com.example.demo.service;

import com.example.demo.model.Books;
import com.example.demo.model.User;
import com.example.demo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BooksService {
    @Autowired
     private BooksRepository booksRepository;
    public Page<Books> getAllBooks(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }
    public List<Books> getAllBooks(){
        return booksRepository.findAll();
    }

    public Books getBookById(Long id){
        return booksRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("user id" + id + "not found"));
    }

    public Books addBooks(Books books){
        return booksRepository.save(books);
    }


    public Books updateBook(Long id, Books updatedBookDetails) {
        // Find the existing book by ID
        return booksRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBookDetails.getTitle());
            existingBook.setIsbn(updatedBookDetails.getIsbn());
            existingBook.setPublished_year(updatedBookDetails.getPublished_year());
            existingBook.setAvailable(updatedBookDetails.getAvailable());
            // Save updated entity
            return booksRepository.save(existingBook);
        }).orElseThrow(() -> new IllegalStateException("Book not found with id " + id));
    }

    public void deleteBook(Long id) {
        if (booksRepository.existsById(id)) {
            booksRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    }

