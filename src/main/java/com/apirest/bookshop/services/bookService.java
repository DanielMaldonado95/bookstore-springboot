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
    String message = "500 Internal Server Error";

    /**
     * Get books from the database
     * 
     * @param page        number of page
     * @param size        size of page
     * @param title       search by title if you want that
     * @param sort        sort by title either by asc or desc
     * @param unavailable books available or unavailable
     * @return return an object type page with the data
     */
    public Map<String, Object> getBooks(int page, int size, String title, String sort, Boolean unavailable) {

        Map<String, Object> bookResponse = new HashMap<String, Object>();
        Sort sortBy = Sort.by(sort.toLowerCase().split(",")[0]);

        Pageable pageable = PageRequest.of(page, size,
                sort.split(",")[1] == "asc" ? sortBy.ascending() : sortBy.descending());
        Page<bookModel> books = null;

        if (title != null && !title.isEmpty()) {
            books = this.repository.searchByTitleIgnoreCaseContaining(title, pageable);
        } else {
            if (unavailable) {
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
     * Save a book in the database
     * 
     * @param book book model
     * @return return a book model
     * @throws Exception return an exception
     */
    public bookModel postBook(bookModel book) throws Exception {

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new Exception(this.message);
        }

        if (!common.isNumeric(Integer.toString(book.getStock())) || book.getStock() < 0) {
            throw new Exception(this.message);
        }

        if (!common.isNumeric(Float.toString(book.getSalePrice())) || book.getSalePrice() <= 0) {
            throw new Exception(this.message);
        }

        if (book.getDescription() != null) {
            book.setDescription("");
        }

        if (book.getAvailable() == null) {
            book.setAvailable(true);
        }

        return this.repository.save(book);
    }

    /**
     * 
     * @param bookId
     * @param bookMoodel
     * @throws Exception
     */
    public bookModel putBook(Long bookId, bookModel bookMoodel) throws Exception {
        bookModel book = this.repository.searchByBookId(bookId);

        if (book.getBookId() <= 0) {
            throw new Exception(this.message);
        }

        book.setTitle(bookMoodel.getTitle());
        if(bookMoodel.getDescription() != null){
            book.setDescription(bookMoodel.getDescription());
        } else{
            book.setDescription("");
        }
        book.setStock(bookMoodel.getStock());
        book.setSalePrice(bookMoodel.getSalePrice());
        if(bookMoodel.getAvailable() != null){
            book.setAvailable(bookMoodel.getAvailable());
        }

        return this.repository.save(book);
    }

    /**
     * 
     * @param bookId
     * @param bookModel
     * @throws Exception
     */
    public bookModel patchBook(Long bookId, bookModel bookModel) throws Exception {
        bookModel book = this.repository.searchByBookId(bookId);

        if (book.getBookId() <= 0) {
            throw new Exception(this.message);
        }

        if (bookModel.getTitle() != null) {
            if (book.getTitle().equals(bookModel.getTitle()) == false) {
                book.setTitle(bookModel.getTitle());
            }
        }

        if (bookModel.getDescription() != null) {
            if (book.getDescription().equals(bookModel.getDescription()) == false) {
                book.setDescription(bookModel.getDescription());
            }
        }

        if (Integer.toString(book.getStock()).equals(Integer.toString(bookModel.getStock())) == false) {
            book.setStock(bookModel.getStock());
        }

        if (Float.toString(book.getSalePrice()).equals(Float.toString(bookModel.getSalePrice())) == false
                && bookModel.getSalePrice() > 0) {
            book.setSalePrice(bookModel.getSalePrice());
        }

        if (bookModel.getAvailable() != null) {
            if (Boolean.toString(book.getAvailable()).equals(Boolean.toString(bookModel.getAvailable())) == false) {
                book.setAvailable(bookModel.getAvailable());
            }
        }

        return this.repository.save(book);
    }

    /**
     * 
     * @param bookId
     * @throws Exception
     */
    public void deleteBook(Long bookId) throws Exception {
        if (bookId != null && bookId <= 0) {
            throw new Exception(this.message);
        }

        this.repository.deleteById(bookId);
    }
}
