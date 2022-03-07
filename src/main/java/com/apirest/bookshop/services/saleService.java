package com.apirest.bookshop.services;

import java.time.LocalDate;

import com.apirest.bookshop.models.bookModel;
import com.apirest.bookshop.models.saleModel;
import com.apirest.bookshop.repositories.bookRepository;
import com.apirest.bookshop.repositories.saleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class saleService {
    
    @Autowired
    saleRepository saleRepository;
    @Autowired   
    bookRepository bookRepository;

    public saleModel postSale(saleModel saleModel) throws Exception{
        LocalDate toDay = LocalDate.now();
        bookModel book = this.bookRepository.searchByBookId(saleModel.getBookId());
        
        if(book.getBookId() <= 0 || book.getStock() == 0){
            throw new Exception("500 Internal Server Error");
        }

        saleModel.setSalePrice(book.getSalePrice());
        saleModel.setSaleDate(toDay);
        return this.saleRepository.save(saleModel);
    }
}
