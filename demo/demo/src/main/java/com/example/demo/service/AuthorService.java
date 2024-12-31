package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Books;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
private AuthorRepository authorRepository;


    public Page<Author> getAuthorPaging(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("author id" + id + "not found"));
    }

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author updatedAuthorDetails) {
        // Find the existing author by ID
        return authorRepository.findById(id).map(existingAuthor -> {
            existingAuthor.setName(updatedAuthorDetails.getName());

            // Save updated entity
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new IllegalStateException("Book not found with id " + id));
    }

    public void deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }



}
