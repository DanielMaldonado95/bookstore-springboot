package com.apirest.bookshop.controllers;

import java.util.Map;
import com.apirest.bookshop.models.bookModel;
import com.apirest.bookshop.services.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        Map<String, Object> response = this.service.getBooks(page == 0 ? page : page - 1, size, title, sort,
                unavailable);
        return response;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Created")
    public bookModel postBook(@RequestBody bookModel book) throws Exception {
        return this.service.postBook(book);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public bookModel putBook(@PathVariable Long bookId, @RequestBody bookModel bookModel) throws Exception {
        return this.service.putBook(bookId, bookModel);
    }

    @PatchMapping(path = "/{bookId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public bookModel patchBook(@PathVariable Long bookId, @RequestBody bookModel bookModel) throws Exception {
        return this.service.patchBook(bookId, bookModel);
    }

    @DeleteMapping(path = "/{bookId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public void deleteBook(@PathVariable Long bookId) throws Exception {
        this.service.deleteBook(bookId);
    }
}
