package com.apirest.bookshop.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.apirest.bookshop.models.bookModel;
import com.apirest.bookshop.models.likeModel;
import com.apirest.bookshop.repositories.bookRepository;
import com.apirest.bookshop.repositories.likeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class likeService {
    
    @Autowired
    likeRepository likeRepository;
    @Autowired
    bookRepository bookRepository;

    public Map<String, Object> postLike(likeModel like) throws Exception{
        
        Map<String, Object> response = new HashMap<String, Object>();
        bookModel book = this.bookRepository.searchByBookId(like.getBookId());
        ArrayList<String> customers = new ArrayList<String>();
        ArrayList<likeModel> customersLike = new ArrayList<likeModel>();
        Long likes = Long.MIN_VALUE;
        
        if(book.getBookId() <= 0 || book.getAvailable() != true){
            throw new Exception("500 Internal Server Error");
        }

        if(this.likeRepository.countByCustomerEmailAndBookId(like.getCustomerEmail(), like.getBookId()) > 0){
            throw new Exception("500 Internal Server Error");
        }

        if(like.getCustomerEmail() == null){
            throw new Exception("500 Internal Server Error");
        }

        this.likeRepository.save(like);
        likes = this.likeRepository.countByBookId(like.getBookId());
        customersLike = this.likeRepository.searchDistinctCustomerEmailByBookId(like.getBookId());
        for (likeModel likeModel : customersLike) {
            customers.add(likeModel.getCustomerEmail());
        }

        response.put("bookId:", like.getBookId());
        response.put("likes:", likes);
        response.put("customers:", customers);

        return response;
    }
}
