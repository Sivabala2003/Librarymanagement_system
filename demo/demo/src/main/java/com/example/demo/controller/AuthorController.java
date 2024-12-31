package com.example.demo.controller;

import com.example.demo.model.Author;

import com.example.demo.service.AuthorService;
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
@RequestMapping("api/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping("/authorPaging")
    public Page<Author> getAuthor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return authorService.getAuthorPaging(pageable);
    }
    @GetMapping("/allAuthor")
    public List<Author> getAllAuthors(){

        return authorService.getAllAuthors();
    }

    @GetMapping("/getAuthorById/{id}")
    public Author getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author author){
        Author addedAuthor =  authorService.addAuthor(author);
        return new ResponseEntity<>(addedAuthor, HttpStatus.OK);
    }

    @PutMapping("updateBook/{id}")

    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Author updatedAuthor = authorService.updateAuthor(id, authorDetails);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("deleteAuthor/{id}")

    public String deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return "Book deleted successfully with ID: " + id;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }


}
