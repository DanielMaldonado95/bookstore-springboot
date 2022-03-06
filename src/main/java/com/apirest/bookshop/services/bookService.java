package com.apirest.bookshop.services;

import java.util.HashMap;
import java.util.Map;

import com.apirest.bookshop.common.common;
import com.apirest.bookshop.models.bookModel;
import com.apirest.bookshop.repositories.bookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class bookService {
    
    @Autowired
    bookRepository repository;

    /**
     * Get books from the database
     * @param page number of page
     * @param size size of page
     * @param title search by title if you want that
     * @param sort sort by title either by asc or desc
     * @param unavailable books available or unavailable
     * @return return an object type page with the data
     */
    public Map<String, Object> getBooks(int page, int size, String title, String sort, Boolean unavailable) {

        Map<String, Object> bookResponse = new HashMap<String, Object>();
        Sort sortBy = Sort.by(sort.toLowerCase().split(",")[0]);
        
        Pageable pageable = PageRequest.of(page, size, sort.split(",")[1] == "asc" ? sortBy.ascending() : sortBy.descending());
        Page<bookModel> books = null;

        if(title != null && !title.isEmpty()){
            books = this.repository.searchByTitleIgnoreCaseContaining(title, pageable);
        } else {
            if(unavailable){
                books = this.repository.searchByAvailable(true, pageable);
            } else {
                books = this.repository.findAll(pageable);
            }
        }
        
        bookResponse.put("Content", books.getContent());
        bookResponse.put("size", size);
        bookResponse.put("numberOfElements", books.getNumberOfElements());
        bookResponse.put("totalElements", books.getTotalElements());
        bookResponse.put("totalPages", books.getTotalPages());
        bookResponse.put("number", books.getNumber() + 1);
    
        return bookResponse;
    }

    /**
     * Save a new book in the database
     * @param book
     * @return
     * @throws Exception
     */
    public bookModel postBook(bookModel book) throws Exception {
        
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new Exception("The title cannot be empty or null ");
        }

        if (!common.isNumeric(Integer.toString(book.getStock())) || book.getStock() < 0) {
            throw new Exception("The stock cannot be a string ");
        }

        if (!common.isNumeric(Float.toString(book.getSalePrice())) || book.getSalePrice() <= 0) {
            throw new Exception("The salePrice cannot be empty, null or less than or equal to zero");
        }

        return this.repository.save(book);
    }
}
