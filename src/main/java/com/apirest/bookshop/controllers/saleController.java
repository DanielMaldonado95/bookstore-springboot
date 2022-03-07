package com.apirest.bookshop.controllers;

import com.apirest.bookshop.models.saleModel;
import com.apirest.bookshop.services.saleService;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class saleController {
    
    @Autowired
    saleService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Ok")
    public saleModel postSale(@RequestBody saleModel sale) throws Exception{
        return this.service.postSale(sale);
    }
}
