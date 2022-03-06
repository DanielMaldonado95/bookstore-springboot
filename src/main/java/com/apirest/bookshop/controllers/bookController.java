package com.apirest.bookshop.controllers;

import java.util.Map;
import com.apirest.bookshop.models.bookModel;
import com.apirest.bookshop.services.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class bookController {
    @Autowired
    bookService service;

    @GetMapping
    public Map<String, Object> getBooks(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "12") Integer size,
            @RequestParam(name = "title", defaultValue = "") String title,
            @RequestParam(name = "sort", defaultValue = "title,asc") String sort,
            @RequestParam(name = "unavailable", defaultValue = "true") Boolean unavailable) {
        
        Map<String, Object> response = this.service.getBooks(page == 0 ? page : page - 1 , size, title, sort, unavailable);
        return response;
    }

    @PostMapping
    public ResponseEntity<?> postBook(@RequestBody bookModel book) {
        try {
            this.service.postBook(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"status\": \"" + "Created" + "\"}");
    }

   /*  @PatchMapping("/{bookId}")
    public ResponseEntity<?> patchBook(@RequestParam Long bookId) {
        try {

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"status\": \"" + "Created" + "\"}");
    } */
}
